package com.example.guestservice.domain.port.out;

import com.example.guestservice.domain.model.Guest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GuestRepositoryPort {
    Guest save(Guest guest);
    Optional<Guest> findById(UUID guestId);
    List<Guest> findByEventId(UUID eventId);
    void deleteById(UUID guestId);
}

