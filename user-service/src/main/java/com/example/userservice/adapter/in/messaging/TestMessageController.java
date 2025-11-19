package com.example.userservice.web;

import com.example.userservice.messaging.UserEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class TestMessageController {

    private final UserEventPublisher publisher;

    public TestMessageController(UserEventPublisher publisher) {
        this.publisher = publisher;
    }

    @GetMapping("/publish-test")
    public ResponseEntity<String> publishTest() {
        publisher.publishUserCreated("{\"id\":\"test-123\",\"event\":\"USER_CREATED\"}");
        return ResponseEntity.ok("Message sent");
    }
}
