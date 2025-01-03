package com.project.agendaWeb.service;

import com.project.agendaWeb.entity.User;
import com.project.agendaWeb.exception.DuplicateEntryException;
import com.project.agendaWeb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User newUser) {
        //Verifica se o email já está cadastrado
        if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            throw new DuplicateEntryException("Email já cadastrado");
        }
        return userRepository.save(newUser);
    }
}
