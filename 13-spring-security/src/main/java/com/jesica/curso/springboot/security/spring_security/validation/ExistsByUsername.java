package com.jesica.curso.springboot.security.spring_security.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ExistsByUsernameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsByUsername {

    String message() default "Ya existe en la base de datos, por favor elija otro nombre de usuario";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };


}
