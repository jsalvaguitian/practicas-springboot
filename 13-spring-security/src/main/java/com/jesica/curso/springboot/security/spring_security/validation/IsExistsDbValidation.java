package com.jesica.curso.springboot.security.spring_security.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jesica.curso.springboot.security.spring_security.services.ProductService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class IsExistsDbValidation implements ConstraintValidator<IsExistsDB, String>{


    @Autowired 
    private ProductService service;


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        
        if (service == null) {
            return true;
        }

        return !service.existsBySku(value);
    }
    
}