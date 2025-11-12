package com.example.userservice.domain.model;

import com.example.userservice.domain.model.vo.EmailAdress;
import com.example.userservice.domain.model.vo.Password;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    private String name;

    @Valid
    @Embedded
    private EmailAdress emailAdress;

    @Valid
    @Embedded
    private Password password;

    private LocalDateTime creatadAt;
}
