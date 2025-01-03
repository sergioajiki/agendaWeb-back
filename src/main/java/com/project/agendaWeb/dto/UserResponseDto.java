package com.project.agendaWeb.dto;

import com.project.agendaWeb.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;

public record UserResponseDto(
        @Schema(description = "Número de identificação do usuário")
        Long id,
        @Schema(description = "Nome do usuário cadastrado")
        String name,
        @Schema(description = "Email do usuário cadastrado")
        String email
) {
    public UserResponseDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String email() {
        return email;
    }

    public static UserResponseDto userResponseDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
