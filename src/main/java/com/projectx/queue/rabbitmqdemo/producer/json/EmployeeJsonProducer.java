package com.projectx.queue.rabbitmqdemo.producer.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectx.queue.rabbitmqdemo.entity.Employee;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("Json")
@Service
public class EmployeeJsonProducer {

    private final static String QUEUE = "course.employee";

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private ObjectMapper mapper;

    public void  sendMessage(Employee data) throws JsonProcessingException {
        String value = mapper.writeValueAsString(data);
        template.convertAndSend(QUEUE, value);
    }
}
