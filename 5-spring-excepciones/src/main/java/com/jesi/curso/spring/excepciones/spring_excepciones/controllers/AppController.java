package com.jesi.curso.spring.excepciones.spring_excepciones.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class AppController {

    @GetMapping("/app")
    public String index() {
        int value = 100/0;
        System.out.println(value);
        return "ok 200";
    }
    

}
