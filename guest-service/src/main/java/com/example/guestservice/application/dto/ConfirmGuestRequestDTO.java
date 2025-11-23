package com.example.guestservice.application.dto;

import com.example.guestservice.domain.model.vo.GuestStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmGuestRequestDTO {
    @NotNull(message = "O status é obrigatório")
    private GuestStatus status;
}

