package com.example.userservice.adapter.in.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.example.userservice.messaging.RabbitConfig;

@Service
public class UserEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public UserEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishUserCreated(String payload) {
        rabbitTemplate.convertAndSend(RabbitConfig.USERS_EXCHANGE, "user.created", payload);
    }
}
