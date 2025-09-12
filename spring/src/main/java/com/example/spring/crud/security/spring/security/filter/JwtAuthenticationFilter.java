package com.example.spring.crud.security.spring.security.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.spring.crud.security.spring.entities.User;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.example.spring.crud.security.spring.security.TokenJwtConfig.*; //importan todas las constantes como si fuese de esta clase con import static

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    
    
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
       this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        //convertir el obj json a un objeto java
        User user=null;
        String username = null;
        String password=null;

        try {
            user= new ObjectMapper().readValue(request.getInputStream(), User.class);
            username = user.getUsername();
            password = user.getPassword(); 
        } catch (StreamReadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DatabindException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        
        return authenticationManager.authenticate(authenticationToken);

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
                
                org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)authResult.getPrincipal();
                String username = user.getUsername();

                Collection<? extends GrantedAuthority>roles =authResult.getAuthorities();

                //roles
                Claims claims = Jwts.claims()
                                .add("authorities", new ObjectMapper().writeValueAsString(roles))
                                .add("username",username)
                                .build();
                //
                String token = Jwts.builder()
                            .subject(username)
                            .claims(claims)//basicamente es una interfaz que hereda del map basicamente es un diccionario
                            .expiration(new Date(System.currentTimeMillis() +3600000))//durara 1 hora de la fecha actual nuestro token
                            .issuedAt(new Date())//la fecha que se creo
                            .signWith(SECRET_KEY)
                            .compact();//genero el tokem con la firma de la llave secreta

                response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);//rspeuesta para el cliente

                //Generamos el formato de nuestro Json como se ver
                Map<String, String> body = new HashMap<>();
                body.put("token", token);
                body.put("username", username);
                body.put("message", String.format("Hola %s has iniciado con exito!", username));

                response.getWriter().write(new ObjectMapper().writeValueAsString(body));
                response.setContentType(CONTENT_TYPE
                );
                response.setStatus(200);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        Map<String, String> body = new HashMap<>();
        body.put("message","Error en la autenticacion username o password incorrectos!");//el mensaje debe ser ambiguo y no indicar especificamente que dato fue correcto como consejo de ciberseguridad
        body.put("error", failed.getMessage());

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(401);//indica error no autorizado
        response.setContentType(CONTENT_TYPE);
    }

    


}
