package com.projectx.queue.rabbitmqdemo.consumer.retry.spring.fanout;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectx.queue.rabbitmqdemo.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("spring.fanout.retry")
@Service
@Slf4j
public class SpringRetryEmployeeConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "q.spring2.accounting.work")
    public void listenAccounting(String message) throws JsonProcessingException {
        var employee = objectMapper.readValue(message, Employee.class);
        log.info("On accounting, consuming {}", employee);

        if (employee.getName() == null || employee.getName().isBlank()) {
            throw new IllegalArgumentException("Name is empty");
        }

        log.info("On accounting, employee {}", employee);
    }

    @RabbitListener(queues = "q.spring2.marketing.work")
    public void listenMarketing(String message) throws JsonProcessingException {
        var employee = objectMapper.readValue(message, Employee.class);
        log.info("On marketing, consuming {}", employee);
        log.info("On marketing, employee {}", employee);
    }
}