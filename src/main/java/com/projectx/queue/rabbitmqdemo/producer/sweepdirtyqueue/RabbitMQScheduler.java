package com.projectx.queue.rabbitmqdemo.producer.sweepdirtyqueue;

import com.projectx.queue.rabbitmqdemo.entity.RabbitMqQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Profile("sweep.dirty.queue")
@Service
@Slf4j
public class RabbitMQScheduler {

    @Autowired
    private RabbitMQClient client;

    @Scheduled(fixedRate = 5000)
    public void sweepDirtyQueues() {
        try {
            var dirtyQueues = client.getAllQueues().stream().filter(RabbitMqQueue::isDirty).toList();
            dirtyQueues.forEach(q -> log.info("Queue {} has {} unprocessed messages", q.getName(), q.getMessage()));
        } catch (Exception ex) {
            log.warn("cannot sweep queue: {}", ex.getMessage());
        }
    }
}
