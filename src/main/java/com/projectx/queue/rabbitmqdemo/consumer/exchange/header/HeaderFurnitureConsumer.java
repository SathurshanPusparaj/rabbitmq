package com.projectx.queue.rabbitmqdemo.consumer.exchange.header;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectx.queue.rabbitmqdemo.entity.Furniture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Profile("header")
@Service
@Slf4j
public class HeaderFurnitureConsumer {

    private static final String COMMA = " , ";

    private static final String PATTERN = "%s - %s";

    @Autowired
    private ObjectMapper mapper;

    @RabbitListener(queues = {"q.promotion.discount", "q.promotion.free-delivery"})
    public void listen(Message messageAmqp) throws JsonProcessingException {
        var body = new String(messageAmqp.getBody());
        var furniture = mapper.readValue(body, Furniture.class);

        var header = messageAmqp.getMessageProperties().getHeaders()
                .entrySet().stream()
                .map((entry) -> String.format(PATTERN, entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(COMMA));

        var queue = messageAmqp.getMessageProperties().getConsumerQueue();

        log.info("consuming: {} queue: {} with routing key : {}", furniture, queue, header);
    }
}
