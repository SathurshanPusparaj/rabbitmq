package com.projectx.queue.rabbitmqdemo.producer;

import com.projectx.queue.rabbitmqdemo.entity.DummyMessage;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("reliable.producer")
@Service
@Slf4j
public class ReliableProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    private void registerCallback() {
        rabbitTemplate.setConfirmCallback(((correlationData, ack, cause) -> {
            if (correlationData == null) {
                return;
            }

            if (ack) {
                log.info("Message with correlation {} published", correlationData.getId());
            } else {
                log.warn("Invalid exchange, message with correlation {} published", correlationData.getId());
            }
        }));

        rabbitTemplate.setReturnsCallback(returned -> {
            log.info("return callback");

            var replyText = returned.getReplyText();
            if (replyText != null && replyText.equalsIgnoreCase("NO_ROUTE")) {
                var id = returned.getMessage().getMessageProperties().getHeader("spring_returned_message_correlation").toString();
                log.warn("Invalid routing key for message {}", id);
            }
        });
    }

    public void sendDummyWithInvalidRoutingKey(DummyMessage message) {
        var correlationData = new CorrelationData(Integer.toString(message.getPublishOrder()));
        rabbitTemplate.convertAndSend("x.dummy2", "invalid-routing-key", message, correlationData);
    }

    public void sendDummyToInvalidExchange(DummyMessage message) {
        var correlationData = new CorrelationData(Integer.toString(message.getPublishOrder()));
        rabbitTemplate.convertAndSend("x.non-exists-exchange", "", message, correlationData);
    }
}
