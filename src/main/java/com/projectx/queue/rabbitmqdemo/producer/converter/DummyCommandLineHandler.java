package com.projectx.queue.rabbitmqdemo.producer.converter;

import com.projectx.queue.rabbitmqdemo.entity.DummyMessage;
import com.projectx.queue.rabbitmqdemo.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Profile("spring.auto.convert")
@Component
public class DummyCommandLineHandler implements CommandLineRunner {

    @Autowired
    private Producer<DummyMessage> producer;

    @Override
    public void run(String... args) throws Exception {
        var message = new DummyMessage("Now is " + LocalDateTime.now(), 1);
        producer.sendMessage(message);
    }
}
