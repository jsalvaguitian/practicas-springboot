package com.example.spring.crud.security.spring.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.spring.crud.security.spring.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByUsername(String username); 
    Optional<User>findByUsername(String username);

}
