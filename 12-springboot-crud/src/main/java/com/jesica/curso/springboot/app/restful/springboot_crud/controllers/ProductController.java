package com.jesica.curso.springboot.app.restful.springboot_crud.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jesica.curso.springboot.app.restful.springboot_crud.entities.Product;
import com.jesica.curso.springboot.app.restful.springboot_crud.services.ProductService;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> list() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> view(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Product productNew = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productNew);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) {
            return validation(result);
        }

        Product productSearched = productService.update(id, product);
        if(productSearched!=null){
             return ResponseEntity.status(HttpStatus.CREATED).body(productSearched);
        }
        return ResponseEntity.notFound().build();
       
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> error = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            error.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        

        //opcion 1
        return ResponseEntity.badRequest().body(error);

        //opcion 2
        //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        
        Product productDeleted = productService.delete(id);
        if (productDeleted != null) {
            return ResponseEntity.ok(productDeleted);
        }
        return ResponseEntity.notFound().build();
    }

    
        
    


     

}
