package com.projectx.queue.rabbitmqdemo.producer.retry.spring.fanout;

import com.projectx.queue.rabbitmqdemo.entity.Employee;
import com.projectx.queue.rabbitmqdemo.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Profile("spring.fanout.retry")
@Component
public class SpringRetryFanOutCommandLineHandler implements CommandLineRunner {

    @Autowired
    private Producer<Employee> producer;

    @Override
    public void run(String... args) throws Exception {
        for (int i=0; i<1; i++) {
            var employee = new Employee("emp"+i, null, LocalDate.now());
            producer.sendMessage(employee);
        }
    }
}
