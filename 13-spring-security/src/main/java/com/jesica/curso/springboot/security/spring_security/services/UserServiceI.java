package com.jesica.curso.springboot.security.spring_security.services;

import java.util.List;

import com.jesica.curso.springboot.security.spring_security.entities.User;

public interface UserServiceI {

    List<User> findAll();

    User save(User user);


}
