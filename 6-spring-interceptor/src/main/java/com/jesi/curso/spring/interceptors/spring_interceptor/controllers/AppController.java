package com.jesi.curso.spring.interceptors.spring_interceptor.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/app")
public class AppController {

    @GetMapping("/test1")
    public Map<String, String> test1(){
        return Collections.singletonMap("message", "handler test1 del controlador AppController");  
    }

    @GetMapping("test2")
    public Map<String, String> test2(){
        return Collections.singletonMap("message", "handler test2 del controlador AppController");  
    }    

}
