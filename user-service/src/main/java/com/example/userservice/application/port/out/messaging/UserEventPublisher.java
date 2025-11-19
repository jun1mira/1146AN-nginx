package com.example.userservice.adapters.out.messaging;

import com.example.userservice.infrastructure.messaging.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public UserEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishUserCreated(String payload) {
        rabbitTemplate.convertAndSend(RabbitConfig.USERS_EXCHANGE, "user.created", payload);
    }
}
