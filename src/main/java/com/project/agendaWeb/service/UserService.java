package com.project.agendaWeb.service;

import com.project.agendaWeb.entity.User;
import com.project.agendaWeb.exception.DuplicateEntryException;
import com.project.agendaWeb.exception.InvalidEmailFormatException;
import com.project.agendaWeb.repository.UserRepository;
import com.project.agendaWeb.util.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User newUser) {
        //Verifica se o formato do email é valido
        boolean isEmail = EmailValidator.isValidEmail(newUser.getEmail());
        if (!isEmail) {
            throw new InvalidEmailFormatException("Invalid email format");
        }

        //Verifica se o email já está cadastrado
        if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            throw new DuplicateEntryException("Email já cadastrado");
        }

        //Criptografa a senha antes de salvar no BD
        newUser.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));

        return userRepository.save(newUser);
    }
}
