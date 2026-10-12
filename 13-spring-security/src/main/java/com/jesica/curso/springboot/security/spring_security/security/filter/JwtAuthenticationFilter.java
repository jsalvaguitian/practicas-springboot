package com.jesica.curso.springboot.security.spring_security.security.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jesica.curso.springboot.security.spring_security.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.core.exc.StreamReadException;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.ObjectMapper;

//importo la clase TokenConfig
import static com.jesica.curso.springboot.security.spring_security.security.TokenJwtConfig.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    // queda en el backend

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        User user = null;
        String username = null;
        String password = null;

        try {
            user = new ObjectMapper().readValue(request.getInputStream(), User.class); // obtenmos nuestro json con
                                                                                       // request.getInputStream() y lo
                                                                                       // convertimos a un objeto User
            username = user.getUsername();
            password = user.getPassword();
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
        String username = user.getUsername();

        //<? extends GrantedAuthority> : significa cualquier tipo que herede de GrantedAuthority
       // Collection<? extends GrantedAuthority> roles = authResult.getAuthorities(); //obtengo los roles
       List<String> roles = authResult.getAuthorities()
        .stream()
        .map(GrantedAuthority::getAuthority)//Convierto los objetos de Spring Security en cadenas simples antes de serializarlos.
        .toList();

        //los roles que obtuvimos lo pasamos a los claims
        Claims claims = Jwts.claims()
                        .add("authorities", roles)
                        .add("username", username) //no es necesario poner esto es solo para ver, ya que es redundante xq ya lo recibe desde subject.
                        .build();

        String token = Jwts.builder()
                                .subject(username) //este dato seria un claims, los roles tambien seran los claims
                                .claims(claims)
                                .expiration(new Date(System.currentTimeMillis() + 3600000)) //nuestro token vale 1 hora desde la fecha actual por eso lo de 3600000 miliseg 
                                .issuedAt(new Date())
                                .signWith(SECRET_KEY)
                                .compact();

        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);

        Map<String, String> body = new HashMap<>();
        body.put("token", token);
        body.put("username", username);
        body.put("message", String.format("Hola %s, iniciaste sesion con exito :)", username));

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType(CONTENT_TYPE);
        response.setStatus(200);
    }

    //Implementacion del metodo cuando falla el login

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {

                Map<String, String> body = new HashMap<>();
                body.put("message", "Error en la autenticacion username o password incorrectos!"); //dar informacion vaga, no especificar cual fue el error por tema de seguridad
                body.put("error", failed.getMessage());

                response.getWriter().write(new ObjectMapper().writeValueAsString(body));
                response.setStatus(401); //No autorizado
                response.setContentType(CONTENT_TYPE);
            }


    


}
