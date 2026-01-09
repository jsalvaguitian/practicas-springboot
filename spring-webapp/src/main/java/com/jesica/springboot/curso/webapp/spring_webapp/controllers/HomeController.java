package com.jesica.springboot.curso.webapp.spring_webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {

    @GetMapping({"","/", "/home"})
    public String home() {
        return "redirect:/list";
        //return "forward:/list";
    }
    

}
