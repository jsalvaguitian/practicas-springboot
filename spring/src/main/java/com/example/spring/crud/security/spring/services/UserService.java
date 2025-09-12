package com.example.spring.crud.security.spring.services;

import java.util.List;

import com.example.spring.crud.security.spring.entities.User;

public interface UserService {

    List<User>findAll();
    User save(User user);
    
    boolean existsByUsername(String username); 

}
