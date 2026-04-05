package com.jesi.curso.spring.excepciones.spring_excepciones.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler({ArithmeticException.class})// Mapeamos el error de division con esta anotacion
    public ResponseEntity<?> divisionPorCero(Exception ex){
        
        return ResponseEntity.internalServerError().body("Error 500");
    }

}
