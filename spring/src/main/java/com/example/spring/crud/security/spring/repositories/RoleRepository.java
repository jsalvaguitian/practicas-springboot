package com.example.spring.crud.security.spring.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.spring.crud.security.spring.entities.Role;
import java.util.Optional;


public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
