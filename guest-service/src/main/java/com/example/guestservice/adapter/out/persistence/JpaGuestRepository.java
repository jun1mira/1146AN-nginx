package com.example.guestservice.adapter.out.persistence;

import com.example.guestservice.domain.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaGuestRepository extends JpaRepository<Guest, UUID> {
    List<Guest> findByEventId(UUID eventId);
}

