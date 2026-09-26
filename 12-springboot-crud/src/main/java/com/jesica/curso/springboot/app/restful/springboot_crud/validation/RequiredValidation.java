package com.jesica.curso.springboot.app.restful.springboot_crud.validation;

import org.springframework.util.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RequiredValidation implements ConstraintValidator<IsRequired, String > {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
       //OPCION 1
       // return (value != null && !value.isEmpty() && !value.isBlank());

       //Opcion 2
       return  StringUtils.hasText(value);
           
    }

}
