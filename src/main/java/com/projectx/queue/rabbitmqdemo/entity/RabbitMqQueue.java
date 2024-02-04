package com.projectx.queue.rabbitmqdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RabbitMqQueue {
    private long message;

    private String name;

    public long getMessage() {
        return message;
    }

    public void setMessage(long message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDirty() {
        return message > 0;
    }

    @Override
    public String toString() {
        return "RabbitMqQueue{" +
                "message=" + message +
                ", name='" + name + '\'' +
                '}';
    }
}
