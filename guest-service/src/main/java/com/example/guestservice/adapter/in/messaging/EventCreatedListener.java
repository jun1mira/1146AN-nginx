package com.example.guestservice.adapter.in.messaging;

import com.example.guestservice.application.service.AddGuestUseCase;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventCreatedListener {

    private final AddGuestUseCase addGuestUseCase;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = RabbitConfig.EVENT_CREATED_QUEUE)
    public void handleEventCreated(String message) {
        try {
            JsonNode eventData = objectMapper.readTree(message);
            UUID eventId = UUID.fromString(eventData.get("eventId").asText());
            String organizerName = eventData.get("organizerName").asText();
            String organizerEmail = eventData.get("organizerEmail").asText();

            addGuestUseCase.execute(eventId, organizerName, organizerEmail);

            log.info("Guest created for event: {}", eventId);
        } catch (Exception e) {
            log.error("Error processing event.created message: {}", e.getMessage(), e);
        }
    }
}

