package com.projectx.queue.rabbitmqdemo.producer.test;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Profile("test")
@RestController
public class sendMessageController {

    private final RabbitTemplate template;

    public sendMessageController(RabbitTemplate template) {
        this.template = template;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        template.convertAndSend(ConfigureMessageQueue.EXCHANGE_NAME, "test.message", message);
        return "We have sent the message! :" + message;
    }
}
