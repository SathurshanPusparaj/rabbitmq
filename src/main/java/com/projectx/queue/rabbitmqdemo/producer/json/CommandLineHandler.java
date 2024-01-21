package com.projectx.queue.rabbitmqdemo.producer.json;

import com.projectx.queue.rabbitmqdemo.entity.Employee;
import com.projectx.queue.rabbitmqdemo.producer.json.EmployeeJsonProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Profile("Json")
@Component
public class CommandLineHandler implements CommandLineRunner {

    @Autowired
    private EmployeeJsonProducer producer;

    public CommandLineHandler(EmployeeJsonProducer producer) {
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
