package com.jesica.curso.springboot.security.spring_security.services;

import java.util.List;

import com.jesica.curso.springboot.security.spring_security.entities.Product;


public interface ProductService{

    List<Product> findAll();

    Product findById(Long id);

    Product save(Product product);

    Product update(Long id, Product product);

    Product delete(Long id);

    boolean existsBySku(String sku);
    

}
