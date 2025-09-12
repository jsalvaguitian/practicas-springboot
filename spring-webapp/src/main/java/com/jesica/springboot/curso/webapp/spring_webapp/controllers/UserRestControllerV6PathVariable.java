package com.jesica.springboot.curso.webapp.spring_webapp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jesica.springboot.curso.webapp.spring_webapp.models.User;
import com.jesica.springboot.curso.webapp.spring_webapp.models.dto.ParamDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/var")
public class UserRestControllerV6PathVariable {

    @Value("${config.username}")
    private String username;

    //@Value("${config.message}")
    //private String message;

    @Value("${config.listOfValues}")//mas automatico
    private List<String> listOfValues;

    @Value("${config.code}")
    private Integer code;

    @Value("#{'${config.listOfValues}'.toUppercase().split(',')}")//mas manual en comparacion listOfValue
    private List<String> valueList;

    @Value("#{'${config.listOfValues}'.toUpperCase()}")//se convierte en un string en mayuscula
    private String valueString;
    
    @Value("#{${config.valuesMap}}")
    private Map<String, Object> valuesMap;

    @Value("#{${config.valuesMap}.product}")
    private String product;

    @Value("#{${config.valuesMap}.price}")
    private Long price;

    @GetMapping("/values")
    public Map<String, Object> values(@Value("${config.message}") String message){
        Map<String,Object> json = new HashMap<>();
        json.put("username", username);
        json.put("code", code);
        json.put("message", message);
        json.put("listOfValues", listOfValues);
        json.put("valueList", valueList);
        json.put("valueString", valueString);
        json.put("valuesMap", valuesMap);
        json.put("value", product);
        json.put("price", price);
        return json;
    }

    @GetMapping("/path/{message}")
    public ParamDto path(@PathVariable String menssaege){
        ParamDto param = new ParamDto();
        param.setMessage(menssaege);
        return param;
    }

    @GetMapping("/mix/{product}/{id}")
    public Map<String, Object> mixPathVar(@PathVariable String product, @PathVariable Long id){
        Map<String, Object> json= new HashMap<>();
        json.put("product", product);
        json.put("id", id);
        return json;
    }

    @PostMapping("/create")
    public User create(@RequestBody User user) {
        //hacer algo con  el usuario o guardarlo en la bbdd
        user.setName(user.getName().toUpperCase());
        return user;
    }
    
    
}
