package com.projectx.queue.rabbitmqdemo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InvoiceCreatedMessage {
    private double amount;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    private String currency;

    private String invoiceNumber;
}
