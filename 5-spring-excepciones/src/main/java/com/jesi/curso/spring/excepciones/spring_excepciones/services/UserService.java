package com.jesi.curso.spring.excepciones.spring_excepciones.services;

import java.util.List;

import com.jesi.curso.spring.excepciones.spring_excepciones.models.domain.User;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    
}
