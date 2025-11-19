package com.example.notificationservice.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String USERS_EXCHANGE = "users.exchange";
    public static final String USER_CREATED_QUEUE = "notifications.user.created";

    @Bean
    public TopicExchange usersExchange() {
        return new TopicExchange(USERS_EXCHANGE, true, false);
    }

    @Bean
    public Queue userCreatedQueue() {
        return QueueBuilder.durable(USER_CREATED_QUEUE).build();
    }

    @Bean
    public Binding userCreatedBinding(Queue userCreatedQueue, TopicExchange usersExchange) {
        return BindingBuilder.bind(userCreatedQueue).to(usersExchange).with("user.created");
    }
}
