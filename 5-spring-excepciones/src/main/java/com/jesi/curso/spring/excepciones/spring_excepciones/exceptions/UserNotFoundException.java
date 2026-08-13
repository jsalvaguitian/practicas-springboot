package com.jesi.curso.spring.excepciones.spring_excepciones.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

}
