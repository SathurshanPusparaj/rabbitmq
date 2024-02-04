package com.projectx.queue.rabbitmqdemo.producer.retry.spring.fanout;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectx.queue.rabbitmqdemo.entity.Employee;
import com.projectx.queue.rabbitmqdemo.producer.Producer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("spring.fanout.retry")
@Service
public class SpringRetryFanOutEmployeeProducer implements Producer<Employee> {

    private final static String EXCHANGE = "x.spring2.work";

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void  sendMessage(Employee data) throws JsonProcessingException {
        String value = mapper.writeValueAsString(data);
        template.convertAndSend(EXCHANGE, "", value);
    }
}
