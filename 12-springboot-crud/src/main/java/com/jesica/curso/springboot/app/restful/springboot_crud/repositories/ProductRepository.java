package com.jesica.curso.springboot.app.restful.springboot_crud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jesica.curso.springboot.app.restful.springboot_crud.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
    boolean existsBySku(String sku);
}
