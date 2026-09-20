package com.jesica.curso.springboot.app.restful.springboot_crud.services;

import java.util.List;

import com.jesica.curso.springboot.app.restful.springboot_crud.entities.Product;

public interface ProductService{

    List<Product> findAll();

    Product findById(Long id);

    Product save(Product product);

    Product delete(Product product);
    

}
