package com.projectx.queue.rabbitmqdemo.producer.exchange.fanout;

import com.projectx.queue.rabbitmqdemo.entity.Employee;
import com.projectx.queue.rabbitmqdemo.producer.exchange.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Profile("fanout")
@Component
@Slf4j
public class FanoutCommandLineHandler implements CommandLineRunner {

    @Autowired
    private Producer<Employee> producer;

    public FanoutCommandLineHandler(HumanResourceProducer producer) {
        this.producer = producer;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i=0; i<5; i++) {
            var employee = new Employee("emp"+i, "Employee " + i, LocalDate.now());
            producer.sendMessage(employee);
        }
    }
}
