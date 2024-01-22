package com.projectx.queue.rabbitmqdemo.producer.exchange.direct;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectx.queue.rabbitmqdemo.entity.Picture;
import com.projectx.queue.rabbitmqdemo.producer.Producer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("direct")
@Service
public class DirectPictureProducer implements Producer<Picture> {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void sendMessage(Picture picture) throws JsonProcessingException {
        var json = mapper.writeValueAsString(picture);
        template.convertAndSend("x.picture", picture.getType(), json);
    }
}
