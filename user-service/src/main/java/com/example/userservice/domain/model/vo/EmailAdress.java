package com.example.userservice.domain.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Embeddable
public class EmailAdress {

    @Email(message = "Email inválido")
    @Column(name = "email_adress")
    private String value;

    public EmailAdress(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("O email é obrigatório!");
        }
        this.value = normalize(value);
    }

    public static EmailAdress of(String value) {
        return new EmailAdress(value);
    }

    private static String normalize(String value) {
        return value == null ? null : value.trim().toLowerCase();
    }
}
