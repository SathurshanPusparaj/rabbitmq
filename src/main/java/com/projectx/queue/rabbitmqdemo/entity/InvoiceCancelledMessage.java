package com.projectx.queue.rabbitmqdemo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InvoiceCancelledMessage {
    private String invoiceNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate cancelDate;

    private String reason;
}
