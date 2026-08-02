package com.jesi.curso.spring.excepciones.spring_excepciones.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.management.relation.RoleResult;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.jesi.curso.spring.excepciones.spring_excepciones.models.Error;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler({ArithmeticException.class})// Mapeamos el error de division con esta anotacion
    public ResponseEntity<Error> divisionPorCero(Exception ex){
        //opcion 1
        //return ResponseEntity.internalServerError().body("Error 500");

        //opcion 2
        Error error = new Error();
        error.setDate(new Date());
        error.setError("Error division por cero");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        //return ResponseEntity.internalServerError().body(error);

        //opcion 3
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);  
    }

    //Opcion 1
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Error> notFoundException(NoHandlerFoundException ex){
        Error error =  new Error();
        error.setDate(new Date());
        error.setError("Api rest no encontrado");
        error.setMessage(ex.getMessage());

        error.setStatus(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }

    //Opcion 2
    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> numberFormatException(Exception ex){
        Map<String, Object> error = new HashMap<>();
        error.put("date", new Date());
        error.put("error", "Numero invalido o incorrecto, no tiene formato de digito");
        error.put("message", ex.getMessage());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return error;
    }

}
