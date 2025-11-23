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
public class AddGuestUseCase {
    private final GuestRepositoryPort repository;

    public Guest execute(UUID eventId, String name, String email) {
        Guest guest = new Guest();
        guest.setEventId(eventId);
        guest.setName(name);
        guest.setEmail(email);
        guest.setStatus(GuestStatus.INVITED);
        guest.setInvitedAt(LocalDateTime.now());

        return repository.save(guest);
    }
}

