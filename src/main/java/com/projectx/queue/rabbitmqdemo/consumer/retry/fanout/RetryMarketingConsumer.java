package com.projectx.queue.rabbitmqdemo.consumer.retry.fanout;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class RetryMarketingConsumer {
	private static final Logger LOG = LoggerFactory.getLogger(RetryMarketingConsumer.class);

	@Autowired
	private ObjectMapper objectMapper;

	@RabbitListener(queues = "q.guideline2.marketing.work")
	public void listen(Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag)
			throws InterruptedException, IOException {
		try {
			var employee = objectMapper.readValue(message.getBody(), Employee.class);
			LOG.info("On marketing : " + employee);
			channel.basicAck(deliveryTag, false);
		} catch (IOException e) {
			LOG.warn("Error processing message : " + new String(message.getBody()) + " : " + e.getMessage());
			channel.basicReject(deliveryTag, false);
		}
	}

}
