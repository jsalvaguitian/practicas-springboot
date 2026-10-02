package com.jesica.curso.springboot.security.spring_security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration 
public class SpringSecurityConfig {
    
    @Bean //Como quiero que se devuelva un componente string con referencia de BCryptPasswordEncoder, lo defino como un Bean para que Spring lo gestione y pueda inyectarlo en otras clases
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
