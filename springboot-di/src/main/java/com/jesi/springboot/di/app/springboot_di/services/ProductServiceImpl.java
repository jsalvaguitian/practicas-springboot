package com.jesi.springboot.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

import com.jesi.springboot.di.app.springboot_di.models.Product;
import com.jesi.springboot.di.app.springboot_di.repositories.ProductRepositoryImpl;

public class ProductServiceImpl implements ProductService {

    private ProductRepositoryImpl repository = new ProductRepositoryImpl();

    @Override
    public List<Product> findAll(){

        return repository.findAll().stream().map(p ->{
            Double priceImp = p.getPrice()*1.25d;
            //Product newProd = new Product(p.getId(),p.getName(),priceImp.longValue()); opcion 1
            Product newProd = (Product) p.clone(); //opcion 2 usando clone
            newProd.setPrice(priceImp.longValue());
            return newProd;
        }).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id){
        return repository.findById(id);
    }
}
