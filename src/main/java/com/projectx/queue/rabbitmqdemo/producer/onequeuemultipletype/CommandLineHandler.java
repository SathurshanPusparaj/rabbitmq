package com.projectx.queue.rabbitmqdemo.producer.onequeuemultipletype;

import com.projectx.queue.rabbitmqdemo.entity.InvoiceCancelledMessage;
import com.projectx.queue.rabbitmqdemo.entity.InvoiceCreatedMessage;
import com.projectx.queue.rabbitmqdemo.entity.InvoicePaidMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@Profile("one.queue.multiple.message.types")
@Component
@Slf4j
public class CommandLineHandler implements CommandLineRunner {

    @Autowired
    private InvoiceProducer producer;

    @Override
    public void run(String... args) throws Exception {
        var randomInvoiceNumber = "INV-" + ThreadLocalRandom.current().nextInt(100, 200);
        var invCreatedMessage = new InvoiceCreatedMessage(152.26, LocalDate.now().minusDays(2), "USD", randomInvoiceNumber);
        producer.sendInvoiceCreated(invCreatedMessage);

        randomInvoiceNumber = "INV-" + ThreadLocalRandom.current().nextInt(200, 300);
        var randomPaymentNumber = "PAY-" + ThreadLocalRandom.current().nextInt(800, 1000);
        var invoicePaidMessage = new InvoicePaidMessage(randomInvoiceNumber, LocalDate.now(), randomPaymentNumber);
        producer.sendInvoicePaid(invoicePaidMessage);

        randomInvoiceNumber = "INV-" + ThreadLocalRandom.current().nextInt(200, 300);
        var invoiceCancelledMessage = new InvoiceCancelledMessage(randomInvoiceNumber, LocalDate.now(), "Just a test");
        producer.sendInvoiceCancelled(invoiceCancelledMessage);
    }
}
