package com.jesi.curso.spring.excepciones.spring_excepciones.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.jesi.curso.spring.excepciones.spring_excepciones.exceptions.UserNotFoundException;
import com.jesi.curso.spring.excepciones.spring_excepciones.models.domain.User;
import com.jesi.curso.spring.excepciones.spring_excepciones.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private UserService service;


    @GetMapping
    public String index() {
        int value = 100/0;
        System.out.println(value);
        return "ok 200";
    }
    @GetMapping("/formato")
    public String probarFormato() {
        int value = Integer.parseInt("10x"); // si salta error, aparecera el mensaje de error que definimos en HandlerExceptionController
        System.out.println(value);
        return "ok 200";
    }

    //Opcion 1 la clasica
    // @GetMapping("/show/{id}")
    // public User show(@PathVariable(name = "id") Long id){
    //     User user = service.findById(id);

    //     if(user == null){
    //         throw new UserNotFoundException("Error el Usuario no existe");
    //     }
    //     System.out.println(user.getLastname());
    //     return user;
    // }
    
    //opcion 2 usando Optional
    @GetMapping("/show/{id}")
    public User show(@PathVariable(name = "id") Long id){
        return service.findById(id).orElseThrow(() -> new UserNotFoundException("Error el Usuario no existe"));
    }

}
