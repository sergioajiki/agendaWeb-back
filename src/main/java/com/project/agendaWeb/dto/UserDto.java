package com.project.agendaWeb.dto;

import com.project.agendaWeb.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDto {
    @Schema(description = "Nome completo do usuário", required = true)
    @NotBlank(message = "O nome é obrigatório.")
    private String name;
    @Schema(description = "E-mail do usuário", required = true)
    @NotBlank(message = "O e-mail é obrigatório.")
    private String email;
    @Schema(description = "Senha do usuário, no mínimo 6 caracteres", required = true)
    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User toEntity(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword()); //Será criptografada no Service
        return user;
    }

}
