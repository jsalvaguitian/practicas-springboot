package com.jesica.curso.springboot.app.restful.springboot_crud.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

//Creando una Anotacion Personalizada
@Constraint(validatedBy = RequiredValidation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD}) 
public @interface  IsRequired {

    String message() default "Es requerido aplicando anotaciones personalizadas";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
