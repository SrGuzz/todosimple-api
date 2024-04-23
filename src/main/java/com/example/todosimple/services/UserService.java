package com.example.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todosimple.models.User;
import com.example.todosimple.repositories.TaskRepository;
import com.example.todosimple.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;


    public User findById(Long id){
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException(
            "Usuario não encontrado para o ID: " + id
        )); //retorna o usuario se tiver dados, -> serve para fazer uma função dentro de uma função, runtime lança uma enceção mais nao para o programa
    }

    
    
    

}
