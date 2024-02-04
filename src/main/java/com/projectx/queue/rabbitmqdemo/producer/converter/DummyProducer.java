package com.projectx.queue.rabbitmqdemo.producer.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.projectx.queue.rabbitmqdemo.entity.DummyMessage;
import com.projectx.queue.rabbitmqdemo.producer.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("spring.auto.convert")
@Service
@Slf4j
public class DummyProducer implements Producer<DummyMessage> {

    @Autowired
    private RabbitTemplate template;

    @Override
    public void sendMessage(DummyMessage data) throws JsonProcessingException {
        template.convertAndSend("x.dummy", "", data);
    }
}
