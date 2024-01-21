package com.projectx.queue.rabbitmqdemo.consumer.dlx.withdlx;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectx.queue.rabbitmqdemo.entity.Picture;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Profile("with-dlx")
@Service
@Slf4j
public class DlxPictureImageConsumer {

    @Autowired
    private ObjectMapper mapper;

    //@RabbitListener(queues = {"q.picture.withdlx"})
    public void listenAndThrow(Message messageAmqp) throws JsonProcessingException {
        var body = new String(messageAmqp.getBody());
        var picture = mapper.readValue(body, Picture.class);

        if (picture.getSize() > 9000) {
            throw new AmqpRejectAndDontRequeueException("Picture size too large: " + picture);
        }

        log.info("consuming picture : {} with routing key : {}",
                picture,
                messageAmqp.getMessageProperties().getReceivedRoutingKey());
    }

    @RabbitListener(queues = {"q.picture.withdlx"})
    public void listenAndReject(Message messageAmqp, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        var body = new String(messageAmqp.getBody());
        var picture = mapper.readValue(body, Picture.class);

        if (picture.getSize() > 9000) {
            channel.basicReject(tag, false);
            log.error("Failed to process the message therefore rejecting the message = {}", picture);
            return;
        }

        log.info("consuming picture : {} with routing key : {}",
                picture,
                messageAmqp.getMessageProperties().getReceivedRoutingKey());

        channel.basicAck(tag, false);
    }
}
