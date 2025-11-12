package com.example.userservice.domain.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Embeddable
public class Password {

    @Column(name = "password", nullable = false)
    private String value;

    private Password(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("A senha é obrigatória!");
        }
        validate(value);
        this.value = value;
    }

    public static Password of(String rawPassword) {
        return new Password(rawPassword);
    }

    public void validate(String password) {
        if (password.length() < 8) {
            throw new IllegalArgumentException("A senha deve ter mais de 8 caracteres");
        }

        if (!password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos uma letra maiúscula");
        }

        if (!password.matches(".*[a-z].*")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos uma letra minúscula");
        }

        if (!password.matches(".*\\d.*")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos um número");
        }
    }
}
