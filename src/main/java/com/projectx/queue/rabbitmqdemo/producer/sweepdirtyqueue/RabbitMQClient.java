package com.projectx.queue.rabbitmqdemo.producer.sweepdirtyqueue;

import com.projectx.queue.rabbitmqdemo.entity.RabbitMqQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.List;

@Profile("sweep.dirty.queue")
@Service
public class RabbitMQClient {

    @Autowired
    private RestTemplate template;

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.localPort}")
    private String localPort;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    public List<RabbitMqQueue> getAllQueues() {
        var entity = new HttpEntity<>(getHeaders(createBasicAuthHeader(username, password)));
        return template.exchange(getQueueUrl(), HttpMethod.GET, entity, new ParameterizedTypeReference<List<RabbitMqQueue>>() {}).getBody();
    }

    private String createBasicAuthHeader(String username, String password) {
        var auth = username+":"+password;
        return "Basic" + Base64.getEncoder().encodeToString(auth.getBytes());
    }

    private String getQueueUrl() {
        return String.format("http://%s:%s/api/queues", host, localPort);
    }

    private HttpHeaders getHeaders(String encodedAuth) {
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.AUTHORIZATION, encodedAuth);
        header.setContentType(MediaType.APPLICATION_JSON);
        return header;
    }
}
