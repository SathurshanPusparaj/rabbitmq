package com.projectx.queue.rabbitmqdemo.consumer.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("test")
@Service
@Slf4j
public class ReceiveMessageHandler {

    public void handleMessage(String messageBody) {
      log.info("Handle Message!!! {} ", messageBody);
    }
}
