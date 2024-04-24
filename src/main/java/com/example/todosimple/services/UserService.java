package com.example.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.todosimple.models.User;
import com.example.todosimple.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User findById(Long id){
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException(
            "Usuario não encontrado para o ID: " + id
        )); //retorna o usuario se tiver dados, -> serve para fazer uma função dentro de uma função, runtime lança uma enceção mais nao para o programa
    }

    @Transactional //Ou salva todos os dados ou nao salva nada
    public User create(User obj){
        obj.setId(null); //seta o id pra null caso o usuario passe um id na criação
        obj = this.userRepository.save(obj);
        return obj;
    }

    @Transactional
    public User update(User obj){
        User newObj = findById(obj.getId());//busca um usuario pelo id 
        newObj.setPassword(obj.getPassword());//troca a senha do ususario
        return this.userRepository.save(newObj);//salva a senha do usuario no banco
    }
    
    public void delete(Long id){
        findById(id);
        try{
            this.userRepository.deleteById(id);
        }catch(Exception e){
            throw new RuntimeException("Não e possivel pois a entidades relacionadas!");
        }
    }
    

}
