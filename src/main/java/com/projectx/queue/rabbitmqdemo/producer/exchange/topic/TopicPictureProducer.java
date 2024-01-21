package com.projectx.queue.rabbitmqdemo.producer.exchange.topic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectx.queue.rabbitmqdemo.entity.Picture;
import com.projectx.queue.rabbitmqdemo.producer.exchange.Producer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("topic")
@Service
public class TopicPictureProducer implements Producer<Picture> {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void sendMessage(Picture pic) throws JsonProcessingException {
        var json = mapper.writeValueAsString(pic);

        String sb = pic.getSource() + "." + (pic.getSize() > 4000 ? "large" : "small") + "." + pic.getType();

        template.convertAndSend("x.picture-topic", sb, json);
    }
}
