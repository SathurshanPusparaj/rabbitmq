package com.projectx.queue.rabbitmqdemo.consumer.onequeuemultipletype;

import com.projectx.queue.rabbitmqdemo.entity.InvoiceCreatedMessage;
import com.projectx.queue.rabbitmqdemo.entity.InvoicePaidMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("one.queue.multiple.message.types")
@Service
@Slf4j
@RabbitListener(queues = "q.invoice")
public class InvoiceConsumer {

    @RabbitHandler
    public void handleInvoiceCreated(InvoiceCreatedMessage message) {
        log.info("Invoice created : {}", message);
    }

    @RabbitHandler
    public void handleInvoicePaid(InvoicePaidMessage message) {
        log.info("Invoice paid : {}", message);
    }

    @RabbitHandler(isDefault = true)
    public void handleDefault(Object message) {
        log.info("Handling default : {}", message);
    }
}
