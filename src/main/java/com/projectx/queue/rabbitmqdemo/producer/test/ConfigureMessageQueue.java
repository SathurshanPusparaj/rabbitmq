package com.projectx.queue.rabbitmqdemo.producer.test;

import com.projectx.queue.rabbitmqdemo.consumer.test.ReceiveMessageHandler;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class ConfigureMessageQueue {

    public static final String EXCHANGE_NAME = "test.exchange";
    public static final String QUEUE_NAME = "test.queue";

    @Bean
    Queue createQueue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding binding(Queue q, TopicExchange exchange) {
        return BindingBuilder.bind(q).to(exchange).with("test.*");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(messageListenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter getMessageListenerAdapter(ReceiveMessageHandler handler) {
        return new MessageListenerAdapter(handler, "handleMessage");
    }

    //@Bean
    public Declarables rabbitmqSchema() {
        return new Declarables(
                new FanoutExchange("x.another-dummy", true, false),
                new Queue("q.another-dummy"),
                new Binding("q.another-dummy", Binding.DestinationType.QUEUE, "x-another-dummy", "", null)
        );
    }


}
