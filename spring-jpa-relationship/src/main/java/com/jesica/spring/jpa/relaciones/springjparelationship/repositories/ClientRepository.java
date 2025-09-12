package com.jesica.spring.jpa.relaciones.springjparelationship.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jesica.spring.jpa.relaciones.springjparelationship.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {

}
