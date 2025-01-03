package com.project.agendaWeb.controller;

import com.project.agendaWeb.dto.UserDto;
import com.project.agendaWeb.dto.UserResponseDto;
import com.project.agendaWeb.entity.User;
import com.project.agendaWeb.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Users", description = "Gerenciamento de usuários")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo usuário", description = "Cria um novo usuário no sistema")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserDto userDto) {
        try {
            //Converte o Dto para entity
            User userToSave = UserDto.toEntity(userDto);

            //Salva user
            User newUser = userService.createUser(userToSave);

            //Converte a entidade para o DTO de resposta
            UserResponseDto responseDto = UserResponseDto.userResponseDto(newUser);

            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
