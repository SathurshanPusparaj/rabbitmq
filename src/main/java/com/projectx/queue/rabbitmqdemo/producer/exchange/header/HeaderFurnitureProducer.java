package com.projectx.queue.rabbitmqdemo.producer.exchange.header;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectx.queue.rabbitmqdemo.entity.Furniture;
import com.projectx.queue.rabbitmqdemo.producer.exchange.Producer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("header")
@Service
public class HeaderFurnitureProducer implements Producer<Furniture> {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void sendMessage(Furniture furniture) throws JsonProcessingException {
        var props = new MessageProperties();
        props.setHeader("color", furniture.getColor());
        props.setHeader("material", furniture.getMaterial());

        String value = mapper.writeValueAsString(furniture);
        var message = new Message(value.getBytes(), props);
        template.send("x.promotion", "", message);
    }
}
