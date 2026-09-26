package com.jesica.curso.springboot.app.restful.springboot_crud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jesica.curso.springboot.app.restful.springboot_crud.entities.Product;
import com.jesica.curso.springboot.app.restful.springboot_crud.repositories.ProductRepository;

@Transactional 
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    
    ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    //@Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Override

    public Product findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override 
    public Product update(Long id, Product product) {
        Product productSearched = repository.findById(id).orElse(null);
        if (productSearched != null) {
            productSearched.setSku(product.getSku());
            productSearched.setName(product.getName());
            productSearched.setPrice(product.getPrice());
            productSearched.setDescription(product.getDescription());
            return repository.save(productSearched);
        }
        return null;
    }

    @Override
    public Product delete(Long id) {
        Product productSearched = repository.findById(id).orElse(null);
        if (productSearched != null) {
            repository.delete(productSearched);
        }
        return productSearched;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsBySku(String sku) {
        return repository.existsBySku(sku);
     }

}
