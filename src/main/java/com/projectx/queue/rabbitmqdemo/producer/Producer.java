package com.projectx.queue.rabbitmqdemo.producer;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface Producer<T> {
    void sendMessage(T data) throws JsonProcessingException;
}
