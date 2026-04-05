 package com.jesica.springboot.curso.webapp.spring_webapp.controllers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.jesica.springboot.curso.webapp.spring_webapp.models.User;



@Controller
public class UserController {
    @GetMapping("/details")
    public String details(){
        return "details";
    }

    
   @GetMapping("/detailsModel")
   public String pasandoDatosALaVista(Model model){
    model.addAttribute("title", "Hola Mundo SpringBoot");
    model.addAttribute("name", "Jesica");
    model.addAttribute("lastname", "Guitian");
    return "details";
   }

   @GetMapping("/detailsMap")
   public String detailsMap(Map<String,Object>modelMap) {
    modelMap.put("title", "Hola Mundo SpringBoot");
    modelMap.put("name", "Jesica");
    modelMap.put("lastname", "Guitian");
       return "details";
   }

   //usando Modelo (objeto User)

   @GetMapping("/details2")
   public String details(Model model){
    User user = new User("Belen", "Guitian");
    model.addAttribute("title", "Hola Spring");
    model.addAttribute("user", user);
    return "details2";
   }

   /*@GetMapping("/list")//sin la etiqueta ModelAttribute
   public String datalist(Model model){
    List<User>users = new ArrayList<>();
    users.add(new User("Jesi","Salva"));
    users.add(new User("Pepita", "Salva", "pepita@mail.com"));
    users.add(new User("Pepon","Guitian", "pepon@mail.com"));
    users.add(new User("Juan", "Doe"));

    model.addAttribute("users", users);
    model.addAttribute("title", "Listado de usuarios");
    return "list";
   }*/

   //Usando la etiqueta ModelAtributte
   @GetMapping("/list")
   public String datalist(Model model){
    
    //model.addAttribute("users", users); //usando la etiqueta ModelAtributte en el metodo usersModel no es necesario
    model.addAttribute("title", "Listado de usuarios");
    return "list";
   }
   
   @ModelAttribute("users")
   public List<User> usersModel(){
    List<User>users = Arrays.asList(
        new User("Jesica", "Salva"),
        new User("Pepa", "Salva", "pepa@mail.com"),
        new User("Pepon", "Salva", "pepon@mail.com"),
        new User("Pablo", "Acevedo")
    );
    return users;

   } 
   
}
