package com.example.guestservice.application.dto;

import com.example.guestservice.domain.model.vo.GuestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuestResponseDTO {
    private UUID guestId;
    private UUID eventId;
    private String name;
    private String email;
    private GuestStatus status;
    private LocalDateTime invitedAt;
    private LocalDateTime respondedAt;
}

