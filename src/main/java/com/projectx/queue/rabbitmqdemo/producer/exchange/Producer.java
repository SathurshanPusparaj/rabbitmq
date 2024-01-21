package com.projectx.queue.rabbitmqdemo.producer.exchange;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface Producer<T> {
    void sendMessage(T data) throws JsonProcessingException;
}
