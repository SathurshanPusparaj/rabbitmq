package com.projectx.queue.rabbitmqdemo.producer.exchange.fanout;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectx.queue.rabbitmqdemo.entity.Employee;
import com.projectx.queue.rabbitmqdemo.producer.exchange.Producer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("fanout")
@Service
public class HumanResourceProducer implements Producer<Employee> {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void sendMessage(Employee data) throws JsonProcessingException {
        String value = mapper.writeValueAsString(data);
        template.convertAndSend("x.hr", "", value);
    }
}
