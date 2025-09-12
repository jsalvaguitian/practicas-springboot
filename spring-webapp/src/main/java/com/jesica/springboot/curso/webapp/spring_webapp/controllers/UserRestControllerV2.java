package com.jesica.springboot.curso.webapp.spring_webapp.controllers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v2")
public class UserRestControllerV2 {

  @GetMapping("/detailsJson")
  @ResponseBody
  public Map<String, Object> details() {
    Map<String, Object> body = new HashMap<>();
    body.put("title", "Hola SpringBoot");
    body.put("name", "Jesica");
    body.put("lastname", "Guitian");
    return body;
  }

  @RequestMapping(path="/details",method = RequestMethod.GET)
  public Map<String, Object> detailsLargo() {
    Map<String, Object> body = new HashMap<>();
    body.put("title", "Hola SpringBoot");
    body.put("name", "Jesica");
    body.put("lastname", "Guitian");
    return body;
  }
}
