  package com.jesica.springboot.curso.webapp.spring_webapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jesica.springboot.curso.webapp.spring_webapp.models.User;
import com.jesica.springboot.curso.webapp.spring_webapp.models.dto.UserDto;

@RestController
@RequestMapping("/api")  
public class UserRestControllerV4Dto {
    @GetMapping(path = "/detailsDto")
    public UserDto details(){
        User user = new User("Jesica", "Salva");
        UserDto userDto = new UserDto();
        userDto.setUser(user);
        userDto.setTitle("Hola mundis");
        return userDto;
    }

    /* en caso de no anidar el objeto user tambien podemos formatear como queremos enviar los datos
     
    public UserDto details(){
        User user = new User("Jesica", "Salva");
        UserDto userDto = new UserDto();
        userDto.setName(user.getName().concat("").concat(user.getLastName()));
        userDto.setTitle("Hola mundis");
        return userDto;
    }
     */
}
