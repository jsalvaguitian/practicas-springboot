package com.jesica.curso.springboot.app.restful.springboot_crud.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.jesica.curso.springboot.app.restful.springboot_crud.services.ProductService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsExistsDbValidation implements ConstraintValidator<IsExistsDb, String>{

    //Inyectamos el servicio
    private final ProductService service;

    IsExistsDbValidation(ProductService service) {
        this.service = service;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !service.existsBySku(value);
            
    }

    
    
}
