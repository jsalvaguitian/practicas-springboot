package com.jesi.springboot.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.jesi.springboot.di.app.springboot_di.models.Product;
import com.jesi.springboot.di.app.springboot_di.repositories.ProductRepository;

//@Component
@Service
public class ProductServiceImpl implements ProductService {

    // private ProductRepositoryImpl repository = new ProductRepositoryImpl();
    /*
     * @Autowired
     * private ProductRepositoryImpl repository;
     */
    // Inyeccion de dependencia mediante atributo
    /*
     * @Autowired
     * private ProductRepository repository;
     */
    @Autowired
    private Environment environment;
    private ProductRepository repository;

    //Inyeccion de dependencia mediante constructor
    //public ProductServiceImpl(@Qualifier("productList") ProductRepository repository) {
    public ProductServiceImpl(@Qualifier("productJson")ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> findAll() {

        return repository.findAll().stream().map(p -> {
            //Double priceImp = p.getPrice() * 1.25d;//Lo comentp para aplicar properties
            Double priceImp = p.getPrice() * environment.getProperty("config.precio.imp", Double.class); //solicito mi variable del properties
            // Product newProd = new Product(p.getId(),p.getName(),priceImp.longValue());
            // opcion 1
             Product newProd = (Product) p.clone(); // opcion 2 usando clone
             newProd.setPrice(priceImp.longValue());
             return newProd;
            //p.setPrice(priceImp.longValue());//esto para probar la anotacion @RequestScope
            //return p;
        }).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id);
    }

}
