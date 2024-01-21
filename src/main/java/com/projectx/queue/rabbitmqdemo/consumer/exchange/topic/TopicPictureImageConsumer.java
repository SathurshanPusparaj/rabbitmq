package com.projectx.queue.rabbitmqdemo.consumer.exchange.topic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectx.queue.rabbitmqdemo.entity.Picture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("topic")
@Service
@Slf4j
public class TopicPictureImageConsumer {

    @Autowired
    private ObjectMapper mapper;

    @RabbitListener(queues = {"q.picture.image", "q.picture.vector", "q.picture.filter", "q.picture.log"})
    public void listen(Message messageAmqp) throws JsonProcessingException {
        var body = new String(messageAmqp.getBody());
        var picture = mapper.readValue(body, Picture.class);
        log.info("consuming picture : {} with routing key : {}",
                picture,
                messageAmqp.getMessageProperties().getReceivedRoutingKey());
    }
}
