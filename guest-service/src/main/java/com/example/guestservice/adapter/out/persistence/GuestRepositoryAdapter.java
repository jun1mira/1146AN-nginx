package com.example.guestservice.adapter.out.persistence;

import com.example.guestservice.domain.model.Guest;
import com.example.guestservice.domain.port.out.GuestRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GuestRepositoryAdapter implements GuestRepositoryPort {

    private final JpaGuestRepository jpaGuestRepository;

    @Override
    public Guest save(Guest guest) {
        return jpaGuestRepository.save(guest);
    }

    @Override
    public Optional<Guest> findById(UUID guestId) {
        return jpaGuestRepository.findById(guestId);
    }

    @Override
    public List<Guest> findByEventId(UUID eventId) {
        return jpaGuestRepository.findByEventId(eventId);
    }

    @Override
    public void deleteById(UUID guestId) {
        jpaGuestRepository.deleteById(guestId);
    }
}

