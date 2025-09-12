package com.jesica.springboot.curso.webapp.spring_webapp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jesica.springboot.curso.webapp.spring_webapp.models.dto.ParamDto;
import com.jesica.springboot.curso.webapp.spring_webapp.models.dto.ParamDtoMix;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/params")
public class UserRestControllerV5RequestParams {
    
    //En el browser localhost:8080/api/params/foo?message=Hola que tal
    //Por defecto el parametro es requerido 
    @GetMapping("/foo")
    public ParamDto foo(@RequestParam String message) {
        ParamDto paramDto = new ParamDto();
        paramDto.setMessage(message);
        return paramDto;
    }
    
    //Parametro no requerido devolvera null podemos controlarlo usando el ternario
    @GetMapping("/fuu")
    public ParamDto fuu(@RequestParam(required = false) String message) {
        ParamDto paramDto = new ParamDto();
        paramDto.setMessage(message!=null? message:"Hola");
        return paramDto;
    }

    //Usando parametro no requerido y defaultvalue en vez de usar un ternario y podemos usar name parar cambiar message de la url por msj
    @GetMapping("/fee")
    public ParamDto fee(@RequestParam(required = false, defaultValue = "Hola soy un msj por defecto", name = "msj") String message) {
        ParamDto paramDto = new ParamDto();
        paramDto.setMessage(message!=null? message:"Hola");
        return paramDto;
    }

    //url a ingresar en el browser: http://localhost 8090/api/params/varios?text=algun mensaje&code=12345
    @GetMapping("/varios")
    public ParamDtoMix variosParam(@RequestParam String text, @RequestParam Integer code){
        ParamDtoMix params = new ParamDtoMix();
        params.setMessage(text);
        params.setCode(code);
        return params;
        
    }

    @GetMapping("/request")
    public ParamDtoMix request(HttpServletRequest request){
        Integer code=0;
        try {
            code=Integer.parseInt(request.getParameter("code"));
        } catch (NumberFormatException e) {
            // TODO: handle exception
        }
        ParamDtoMix params = new ParamDtoMix();
        params.setCode(code);
        params.setMessage(request.getParameter("message"));
        return params;
    }

}
