package com.projectx.queue.rabbitmqdemo.producer.onequeuemultipletype;

import com.projectx.queue.rabbitmqdemo.entity.InvoiceCancelledMessage;
import com.projectx.queue.rabbitmqdemo.entity.InvoiceCreatedMessage;
import com.projectx.queue.rabbitmqdemo.entity.InvoicePaidMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("one.queue.multiple.message.types")
@Service
@Slf4j
public class InvoiceProducer {

    private static final String EXCHANGE = "x.invoice";

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendInvoiceCreated(InvoiceCreatedMessage message) {
        rabbitTemplate.convertAndSend(EXCHANGE, "", message);
    }

    public void sendInvoicePaid(InvoicePaidMessage message) {
        rabbitTemplate.convertAndSend(EXCHANGE, "", message);
    }

    public void sendInvoiceCancelled(InvoiceCancelledMessage message) {
        rabbitTemplate.convertAndSend(EXCHANGE, "", message);
    }
}
