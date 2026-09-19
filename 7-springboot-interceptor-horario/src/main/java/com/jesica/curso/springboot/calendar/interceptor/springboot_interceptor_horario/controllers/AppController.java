package com.jesica.curso.springboot.calendar.interceptor.springboot_interceptor_horario.controllers;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController 
public class AppController {

    @GetMapping("/test1")
    public ResponseEntity<?> test1(HttpServletRequest request){
        Map<String, Object> data = new HashMap<>();
        data.put("title", "Bienvenido al sistema de atencion!");
        //data.put("time", new Date());
        data.put("time", ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires")));
        //recomendable usar Instant.now() para obtener la fecha y hora actual de modo universal, es decir, 
        // sin importar la zona horaria del servidor y luego segun el usuario, se puede convertir a la zona horaria que corresponda
        
        data.put("message", request.getAttribute("message"));
        return ResponseEntity.ok(data);

    }

}
