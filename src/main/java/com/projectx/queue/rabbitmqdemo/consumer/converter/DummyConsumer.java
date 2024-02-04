package com.projectx.queue.rabbitmqdemo.consumer.converter;

import com.projectx.queue.rabbitmqdemo.entity.DummyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("spring.auto.convert")
@Service
@Slf4j
public class DummyConsumer {

    @RabbitListener(queues = "q.dummy", containerFactory = "prefetchOneContainerFactory")
    public void listenDummy(DummyMessage message) {
        log.info("Message is {}", message);
    }
}
