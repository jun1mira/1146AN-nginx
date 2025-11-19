package com.example.userservice.application.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestDTO {
    
    private String name;  // Opcional
    
    @Email(message = "Email inválido")
    private String email;  // Opcional, mas se preenchido deve ser válido
}

