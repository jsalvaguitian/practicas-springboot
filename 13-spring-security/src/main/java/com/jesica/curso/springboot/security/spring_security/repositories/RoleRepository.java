package com.jesica.curso.springboot.security.spring_security.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.jesica.curso.springboot.security.spring_security.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
