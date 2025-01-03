package com.project.agendaWeb.dto;

import com.project.agendaWeb.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDto {
    @Schema(description = "Nome completo do usuário", required = true)
    @NotBlank(message = "O nome é obrigatório.")
    private String name;
    @Schema(description = "Email do usuário", required = true)
    @NotBlank(message = "O email é obrigatório.")
    private String email;
    @Schema(description = "Senha do usuário, entre 6 e 10 caracteres", required = true)
    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 6, max = 10, message = "A senha deve ter entre 6 e 10 caracteres.")
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
