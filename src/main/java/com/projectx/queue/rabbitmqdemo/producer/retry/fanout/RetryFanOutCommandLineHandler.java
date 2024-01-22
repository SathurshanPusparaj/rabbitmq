package com.projectx.queue.rabbitmqdemo.producer.retry.fanout;

import com.projectx.queue.rabbitmqdemo.entity.Employee;
import com.projectx.queue.rabbitmqdemo.producer.Producer;
import com.projectx.queue.rabbitmqdemo.producer.json.EmployeeJsonProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Profile("retry.fanout")
@Component
public class RetryFanOutCommandLineHandler implements CommandLineRunner {

    @Autowired
    private Producer<Employee> producer;

    @Override
    public void run(String... args) throws Exception {
        for (int i=0; i<5; i++) {
            var employee = new Employee("emp"+i, null, LocalDate.now());
            producer.sendMessage(employee);
        }
    }
}
