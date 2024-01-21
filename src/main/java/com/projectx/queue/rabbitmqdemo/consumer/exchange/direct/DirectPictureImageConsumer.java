package com.projectx.queue.rabbitmqdemo.consumer.exchange.direct;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectx.queue.rabbitmqdemo.entity.Picture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("direct")
@Service
@Slf4j
public class DirectPictureImageConsumer {

    @Autowired
    private ObjectMapper mapper;

    @RabbitListener(queues = "q.picture.image")
    public void listen(String message) throws JsonProcessingException {
        var picture = mapper.readValue(message, Picture.class);
        log.info("On image : {}", picture);
    }
}
