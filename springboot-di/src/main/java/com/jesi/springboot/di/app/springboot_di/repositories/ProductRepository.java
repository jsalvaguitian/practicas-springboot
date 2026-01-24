package com.jesi.springboot.di.app.springboot_di.repositories;

import java.util.Arrays;
import java.util.List;

import com.jesi.springboot.di.app.springboot_di.models.Product;

public class ProductRepository {
    private List<Product> data;

    public ProductRepository() {
        this.data = Arrays.asList(
                new Product(1L, "Memoria Corsair 32", 300L),
                new Product(2L, "Teclado Logitech G19", 100L),
                new Product(3L, "Motherboard Gigabyte", 530L),
                new Product(4L, "Monitor Samsung 24'", 230L)

        );
    }

    public List<Product> findAll(){
        return this.data;
    }

    //aplicando API stream
    public Product findById(Long id){

        return data.stream().filter(p-> p.getId().equals(id)).findFirst().orElse(null);

    }

}
