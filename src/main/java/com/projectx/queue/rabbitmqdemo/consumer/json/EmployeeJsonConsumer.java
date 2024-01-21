package com.projectx.queue.rabbitmqdemo.consumer.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectx.queue.rabbitmqdemo.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("Json")
@Service
@Slf4j
public class EmployeeJsonConsumer {
    private final static String QUEUE = "course.employee";

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = QUEUE)
    public void listen(String message) throws JsonProcessingException {
        var employee = objectMapper.readValue(message, Employee.class);
        log.info("Employee is {}", employee);
    }
}
