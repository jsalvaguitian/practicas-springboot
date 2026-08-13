package com.jesi.curso.spring.excepciones.spring_excepciones.services;

import java.util.List;
import java.util.Optional;

import com.jesi.curso.spring.excepciones.spring_excepciones.models.domain.User;

public interface UserService {
    List<User> findAll();
    //Opcion 1
    //User findById(Long id);

    //Opcion 2 usando Optional
    Optional<User> findById(Long id);
    
}
