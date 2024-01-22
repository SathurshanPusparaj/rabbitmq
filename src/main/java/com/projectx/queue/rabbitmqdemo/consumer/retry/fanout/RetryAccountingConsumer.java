package com.projectx.queue.rabbitmqdemo.consumer.retry.fanout;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectx.queue.rabbitmqdemo.consumer.retry.direct.DlxProcessingErrorHandler;
import com.projectx.queue.rabbitmqdemo.entity.Employee;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Profile("retry.fanout")
@Service
public class RetryAccountingConsumer {
	private static final String DEAD_EXCHANGE_NAME = "x.guideline2.dead";
	private static final Logger LOG = LoggerFactory.getLogger(RetryAccountingConsumer.class);

	@Autowired
	private ObjectMapper objectMapper;

	private final DlxProcessingErrorHandler dlxProcessingErrorHandler;

	public RetryAccountingConsumer() {
		this.dlxProcessingErrorHandler = new DlxProcessingErrorHandler(DEAD_EXCHANGE_NAME);
	}

	@RabbitListener(queues = "q.guideline2.accounting.work")
	public void listen(Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag)
			throws InterruptedException, IOException {
		try {
			var emp = objectMapper.readValue(message.getBody(), Employee.class);

			if (emp.getName() == null || emp.getName().isBlank()) {
				throw new IllegalArgumentException("Name is empty");
			}
			LOG.info("On accounting : {}", emp);
			channel.basicAck(deliveryTag, false);
		} catch (Exception e) {
			LOG.warn("Error processing message : " + new String(message.getBody()) + " : " + e.getMessage());
			dlxProcessingErrorHandler.handleErrorProcessingMessage(message, channel, deliveryTag);
		}
	}

}
