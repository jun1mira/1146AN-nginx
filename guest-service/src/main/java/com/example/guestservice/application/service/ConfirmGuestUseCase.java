package com.example.guestservice.application.service;

import com.example.guestservice.domain.model.Guest;
import com.example.guestservice.domain.model.vo.GuestStatus;
import com.example.guestservice.domain.port.out.GuestRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConfirmGuestUseCase {
    private final GuestRepositoryPort repository;

    public Guest execute(UUID guestId, GuestStatus status) {
        Guest guest = repository.findById(guestId)
                .orElseThrow(() -> new RuntimeException("Guest not found"));

        guest.setStatus(status);
        guest.setRespondedAt(LocalDateTime.now());

        return repository.save(guest);
    }
}

