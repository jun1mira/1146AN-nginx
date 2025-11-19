package com.example.notificationservice.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventListener {

    private static final Logger log = LoggerFactory.getLogger(UserEventListener.class);

    @RabbitListener(queues = RabbitConfig.USER_CREATED_QUEUE)
    public void onUserCreated(String message) {
        log.info("Received USER_CREATED: {}", message);
        // TODO: trigger email/SMS/etc.
    }
}
