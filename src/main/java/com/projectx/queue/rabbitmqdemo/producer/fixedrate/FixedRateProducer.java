package com.projectx.queue.rabbitmqdemo.producer.fixedrate;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


/**
 * Create course.fixedrate queue if missing
 */

@Profile("FixedRate")
@Service
public class FixedRateProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private int i = 0;

    @Scheduled(fixedRate = 500)
    public void sendMessage() {
        i++;
        rabbitTemplate.convertAndSend("course.fixedrate", "Fixed rate" + i);
    }
}
