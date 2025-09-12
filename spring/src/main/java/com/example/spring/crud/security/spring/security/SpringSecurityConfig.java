package com.example.spring.crud.security.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.spring.crud.security.spring.security.filter.JwtAuthenticationFilter;

@Configuration
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean //generamos el objeto autenticationManager con la anotacion Bean con el fin de utilizarlo y sincronizarlo con el filtro
    AuthenticationManager authenticationManager() throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //Configuramos reglas de seguridad en http para endpoint como permisos

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.authorizeHttpRequests((authz)-> authz
        .requestMatchers(HttpMethod.GET, "/api/users").permitAll()
        .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll()
        .anyRequest().authenticated())
        .addFilter(new JwtAuthenticationFilter(authenticationManager()))//agregamos el filtro de autenticacion JWT del login
        .csrf(config -> config.disable())
        .sessionManagement(management-> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build();
    }

    

}
