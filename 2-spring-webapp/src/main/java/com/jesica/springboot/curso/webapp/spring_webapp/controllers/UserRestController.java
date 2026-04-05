 package com.jesica.springboot.curso.webapp.spring_webapp.controllers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class UserRestController {
  @GetMapping("/detailsJson")
    public Map<String, Object> details(){
       Map<String, Object> body = new HashMap<>();
       body.put("title", "Hola SpringBoot");
       body.put("name", "Jesica");
       body.put("lastname", "Guitian");
        return body;
    }
}
