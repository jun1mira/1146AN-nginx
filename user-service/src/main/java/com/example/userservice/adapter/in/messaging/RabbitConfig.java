package com.example.userservice.infrastructure.messaging;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String USERS_EXCHANGE = "users.exchange";

    @Bean
    public TopicExchange usersExchange() {
        return new TopicExchange(USERS_EXCHANGE, true, false);
    }
}
