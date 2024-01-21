package com.projectx.queue.rabbitmqdemo.producer.exchange.header;

import com.projectx.queue.rabbitmqdemo.entity.Furniture;
import com.projectx.queue.rabbitmqdemo.producer.exchange.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Profile("header")
@Component
@Slf4j
public class HeaderCommandLineHandler implements CommandLineRunner {

    @Autowired
    private Producer<Furniture> producer;

    private final List<String> COLOURS = List.of("white", "red", "green");
    private final List<String> MATERIALS = List.of("wood", "plastic", "steel");

    @Override
    public void run(String... args) throws Exception {
        for (int i=0; i< 10; i++) {
            var fur = new Furniture("Picture "+i, COLOURS.get(i % COLOURS.size()),
                    MATERIALS.get(i % MATERIALS.size()), ThreadLocalRandom.current().nextLong(1, 10000));
            producer.sendMessage(fur);
        }
    }
}
