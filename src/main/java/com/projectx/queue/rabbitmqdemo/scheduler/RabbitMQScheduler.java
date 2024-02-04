package com.projectx.queue.rabbitmqdemo.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Profile("spring.auto.scheduler")
@Service
@Slf4j
public class RabbitMQScheduler {

    @Autowired
    private RabbitListenerEndpointRegistry registry;

    @Scheduled(cron = "0 0 23 * * *")
    public void stopAll() {
        registry.getListenerContainers().forEach(c -> {
            log.info("Stopping listener container {}", c);
            c.stop();
        });
    }

    @Scheduled(cron = "1 0 0 * * *")
    public void startAll() {
        registry.getListenerContainers().forEach(c -> {
            log.info("Starting listener container {}", c);
            c.start();
        });
    }
}
