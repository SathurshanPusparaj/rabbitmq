package com.projectx.queue.rabbitmqdemo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DummyMessage {
    private String content;

    private int publishOrder;

    public DummyMessage() {
    }

    public DummyMessage(String content, int publishOrder) {
        this.content = content;
        this.publishOrder = publishOrder;
    }

    @Override
    public String toString() {
        return "DummyMessage{" +
                "content='" + content + '\'' +
                ", publishOrder=" + publishOrder +
                '}';
    }
}
