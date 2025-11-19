package com.example.userservice.application.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UsuarioRepository repository;

    public UserService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void salvarUsuario(User usuario){
        repository.saveAndFlush(usuario);
    }

    public User buscarUsuarioPorEmail(String email){
        return repository.findByEmail(email).orElseThrow(
            () -> new RuntimeException("Email não encontrado")
        );
    }

    public void deletarUsuarioPorEmail(String email){
        repository.deleteByEmail(email);
    }

    public void atualizarUsuarioPorId(Integer id, User usuario){
        User usuarioEntity = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Usuario não encontrado"));
        User usuarioAtualizado = User.builder()
                .email(usuario.getEmail() != null ? usuario.getEmail() : usuarioEntity.getEmail())
                .nome(usuario.getNome() != null ? usuario.getNome() : usuarioEntity.getNome())
                .id(usuarioEntity.getId())
                .build();

        repository.saveAndFlush(usuarioAtualizado);
    }
}
