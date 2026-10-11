package com.jesica.curso.springboot.security.spring_security.validation;

import org.springframework.stereotype.Component;

import com.jesica.curso.springboot.security.spring_security.services.UserServiceI;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * ExistsByUsernameValidator
 */
@Component 
public class ExistsByUsernameValidator implements ConstraintValidator<ExistsByUsername, String> {

    private final UserServiceI serv;
    public ExistsByUsernameValidator(UserServiceI serv) {
        this.serv = serv;
    }
    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
       return !serv.existsByUsername(username);
    }

    

}
