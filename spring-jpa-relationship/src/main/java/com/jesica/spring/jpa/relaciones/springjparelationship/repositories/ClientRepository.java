package com.jesica.spring.jpa.relaciones.springjparelationship.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jesica.spring.jpa.relaciones.springjparelationship.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {

    @Query("select c from Client c left join fetch c.addresses where c.id=?1") 
    Optional<Client> findOneWithAddresses(Long id);

    @Query("select c from Client c left join fetch c.invoices where c.id=?1") 
    Optional<Client> findOneWithInvoices(Long id);

    @Query("select c from Client c left join fetch c.invoices left join fetch c.addresses where c.id=?1") 
    Optional<Client> findOne(Long id);

}
