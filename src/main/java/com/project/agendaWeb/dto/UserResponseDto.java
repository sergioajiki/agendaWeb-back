package com.project.agendaWeb.dto;

import com.project.agendaWeb.entity.User;

public class UserResponseDto {
    private final Long id;
    private final String name;
    private final String email;

    public UserResponseDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
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
