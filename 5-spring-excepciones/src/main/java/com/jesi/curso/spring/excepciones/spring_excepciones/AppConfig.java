package com.jesi.curso.spring.excepciones.spring_excepciones;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jesi.curso.spring.excepciones.spring_excepciones.models.domain.User;

@Configuration
public class AppConfig {

    @Bean
    List<User> users(){
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "Jesi", "Sanchez"));
        users.add(new User(2L, "Marge", "Simpson"));
        users.add(new User(3L, "Jazmin", "Gonzalez"));
        users.add(new User(4L, "Erica", "Benitez"));
        users.add(new User(5L, "Belen", "Guitian"));
        return users;
    }


}
