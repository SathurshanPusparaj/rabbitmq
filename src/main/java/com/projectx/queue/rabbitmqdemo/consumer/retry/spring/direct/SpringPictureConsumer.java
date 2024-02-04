package com.projectx.queue.rabbitmqdemo.consumer.retry.spring.direct;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectx.queue.rabbitmqdemo.entity.Picture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("spring.direct.retry")
@Service
@Slf4j
public class SpringPictureConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "q.spring.image.work")
    public void listenImage(String message) throws JsonProcessingException {
        var picture = objectMapper.readValue(message, Picture.class);
        log.info("Consuming image {}", picture.getName());

        if (picture.getSize() > 9000) {
            throw new IllegalArgumentException("Image too large : " + picture.getName());
        }
        log.info("Processing image : " + picture.getName());
    }

    @RabbitListener(queues = "q.spring.vector.work")
    public void listenVector(String message) throws JsonProcessingException {
        var picture = objectMapper.readValue(message, Picture.class);
        log.info("Consuming vector {}", picture.getName());
        log.info("Processing vector : " + picture.getName());
    }
}
