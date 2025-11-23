package com.example.guestservice.application.service;

import com.example.guestservice.domain.model.Guest;
import com.example.guestservice.domain.port.out.GuestRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ListGuestsUseCase {
    private final GuestRepositoryPort repository;

    public List<Guest> execute(UUID eventId) {
        return repository.findByEventId(eventId);
    }
}

