package com.example.userservice.infrastructure.adapter.in.rest;

import com.example.userservice.application.dto.CreateUserRequestDTO;
import com.example.userservice.application.dto.UpdateUserRequestDTO;
import com.example.userservice.application.dto.UserResponseDTO;
import com.example.userservice.application.usecases.CreateUserUseCase;
import com.example.userservice.application.usecases.DeleteUserUseCase;
import com.example.userservice.application.usecases.ListUsersUseCase;
import com.example.userservice.application.usecases.UpdateUserUseCase;
import com.example.userservice.domain.model.User;
import com.example.userservice.domain.model.vo.EmailAdress;
import com.example.userservice.domain.model.vo.Password;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final ListUsersUseCase listUsersUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(
            @Valid @RequestBody CreateUserRequestDTO request
    ) {

        User user = new User();
        user.setName(request.getName());
        user.setEmailAdress(EmailAdress.of(request.getEmail()));
        user.setPassword(Password.of(request.getPassword()));

        User createdUser = createUserUseCase.execute(user);

        UserResponseDTO response = toResponseDTO(createdUser);

        URI location = URI.create("/api/users/" + createdUser.getUserId());
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listUsers() {

        List<User> users = listUsersUseCase.execute();

        List<UserResponseDTO> responses = users.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(
            @PathVariable UUID userId
    ) {

        User user = listUsersUseCase.findById(userId);


        UserResponseDTO response = toResponseDTO(user);


        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable UUID userId,
            @Valid @RequestBody UpdateUserRequestDTO request
    ) {

        User updatedUser = updateUserUseCase.execute(userId, request);

        UserResponseDTO response = toResponseDTO(updatedUser);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable UUID userId
    ) {

        deleteUserUseCase.execute(userId);

        return ResponseEntity.noContent().build();
    }

    private UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setUserId(user.getUserId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmailAdress().getValue());
        dto.setCreatedAt(user.getCreatadAt());
        return dto;
    }
}
