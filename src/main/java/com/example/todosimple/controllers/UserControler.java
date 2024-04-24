package com.example.todosimple.controllers;

import java.net.URI;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.todosimple.models.User;
import com.example.todosimple.models.User.CreateUser;
import com.example.todosimple.services.UserService;

import jakarta.validation.Valid;




@RestController//avisa o spring q e um controlador 
@RequestMapping("/user") //tudo na rota a partir daqui tem q ter /user 
@Validated //o controler usa todas as notaçoes de validação
public class UserControler {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}") //avisa que a função busca alguma coisa, e diz a informação q eu to buscando na rota
    public ResponseEntity<User> findById(@PathVariable Long id){ //ResponseEntity<Entidade> informa pro front o tipo de entidade que ta sendo retornado, O PathVariable informa pro spring que o id recebido na rota e o mesmo da função
        User obj = this.userService.findById(id);
        return ResponseEntity.ok().body(obj); //retorna que ta tudo ok com o obj enviado ao body do HTML
    } 

    @PostMapping //avisa que a função cria alguma coisa
    @Validated(CreateUser.class) //usa todas as notações de validação criadas na classe
    public ResponseEntity<Void> create(@Valid @RequestBody User obj){
        this.userService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); //mt complexo ate pra comentar
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}") //avisa que a função atualiza alguma coisa
    @Validated(Update.class)
    public ResponseEntity<Void> update(@Valid @RequestBody User obj, @PathVariable Long id){
        obj.setId(id);
        this.userService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete( @PathVariable Long id){
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
