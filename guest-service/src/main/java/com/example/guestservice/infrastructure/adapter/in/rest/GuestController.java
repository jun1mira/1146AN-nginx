package com.example.guestservice.infrastructure.adapter.in.rest;

import com.example.guestservice.application.dto.AddGuestRequestDTO;
import com.example.guestservice.application.dto.ConfirmGuestRequestDTO;
import com.example.guestservice.application.dto.GuestResponseDTO;
import com.example.guestservice.application.service.AddGuestUseCase;
import com.example.guestservice.application.service.ConfirmGuestUseCase;
import com.example.guestservice.application.service.ListGuestsUseCase;
import com.example.guestservice.domain.model.Guest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/guests")
@RequiredArgsConstructor
public class GuestController {

    private final AddGuestUseCase addGuestUseCase;
    private final ListGuestsUseCase listGuestsUseCase;
    private final ConfirmGuestUseCase confirmGuestUseCase;

    @PostMapping
    public ResponseEntity<GuestResponseDTO> addGuest(
            @Valid @RequestBody AddGuestRequestDTO request
    ) {
        Guest guest = addGuestUseCase.execute(
                request.getEventId(),
                request.getName(),
                request.getEmail()
        );

        GuestResponseDTO response = toResponseDTO(guest);
        URI location = URI.create("/api/guests/" + guest.getGuestId());
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<GuestResponseDTO>> listGuestsByEvent(
            @PathVariable UUID eventId
    ) {
        List<Guest> guests = listGuestsUseCase.execute(eventId);

        List<GuestResponseDTO> responses = guests.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{guestId}/confirm")
    public ResponseEntity<GuestResponseDTO> confirmGuest(
            @PathVariable UUID guestId,
            @Valid @RequestBody ConfirmGuestRequestDTO request
    ) {
        Guest guest = confirmGuestUseCase.execute(guestId, request.getStatus());

        GuestResponseDTO response = toResponseDTO(guest);
        return ResponseEntity.ok(response);
    }

    private GuestResponseDTO toResponseDTO(Guest guest) {
        GuestResponseDTO dto = new GuestResponseDTO();
        dto.setGuestId(guest.getGuestId());
        dto.setEventId(guest.getEventId());
        dto.setName(guest.getName());
        dto.setEmail(guest.getEmail());
        dto.setStatus(guest.getStatus());
        dto.setInvitedAt(guest.getInvitedAt());
        dto.setRespondedAt(guest.getRespondedAt());
        return dto;
    }
}

