package com.jesica.springboot.curso.webapp.spring_webapp.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jesica.springboot.curso.webapp.spring_webapp.models.User;

@RestController
@RequestMapping("/api")
public class UserRestControllerV3Model {

  @GetMapping("/details")
  public Map<String, Object> details() {
    User user = new User("Belen", "Guitian");
    Map<String, Object> body = new HashMap<>();
    body.put("title", "Hola SpringBoot");
    body.put("user", user);
    
    return body;
  }

  //api lista

  @GetMapping("/list")
  public List<User> dataList(){
    User user = new User("Jesi","Salva");
    User user2= new User("Belen", "Guitian");
    User user3 = new User("Ana","Doe");
    
    //1era forma
    /*List<User>users = new ArrayList<>();
    users.add(user);
    users.add(user2);
    users.add(user3);
*/
    //2da forma
    List<User> users = Arrays.asList(user,user2,user3);
    return users;
  }
}
