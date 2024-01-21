package com.projectx.queue.rabbitmqdemo.producer.dlx.withdlx;

import com.projectx.queue.rabbitmqdemo.entity.Picture;
import com.projectx.queue.rabbitmqdemo.producer.exchange.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Profile("with-dlx")
@Component
@Slf4j
public class DlxCommandLineHandler implements CommandLineRunner {

    @Autowired
    private Producer<Picture> producer;

    private final List<String> SOURCES = List.of("mobile", "web");
    private final List<String> TYPES = List.of("jpg", "png", "svg");

    @Override
    public void run(String... args) throws Exception {
        for (int i=0; i< 10; i++) {
            var pic = new Picture("Picture "+i, TYPES.get(i % TYPES.size()),
                    SOURCES.get(i % SOURCES.size()), ThreadLocalRandom.current().nextLong(1, 10000));
            producer.sendMessage(pic);
        }
    }
}
