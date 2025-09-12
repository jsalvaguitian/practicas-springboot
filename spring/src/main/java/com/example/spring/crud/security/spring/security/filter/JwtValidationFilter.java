package com.example.spring.crud.security.spring.security.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.example.spring.crud.security.spring.security.TokenJwtConfig.*;//para tener mi header_authorization

public class JwtValidationFilter extends BasicAuthenticationFilter{

    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        //TODO Auto-generated constructor stub
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String header = request.getHeader(HEADER_AUTHORIZATION);//necesitamos el token el cual lo obtenemos en la cabecera desde el cliente 

        if(header==null || !header.startsWith(PREFIX_TOKEN )){
            return;//nos salimos si el header es nulo o no empieza con Bearer que el token viene de modo standard con eso
        }

        String token = header.replace(PREFIX_TOKEN, "");

        //Como puede surgir ciertos errores recomendable usar excepcion
        try {
            Claims claims = Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
           //opcion 1
            String username = claims.getSubject(); 
            //opcion 2
            String username2 = (String) claims.get("username");

            Object authoritiesClaims = claims.get("authorities");

            Collection<? extends GrantedAuthority> authorities = Arrays.asList(new ObjectMapper().readValue(authoritiesClaims.toString().getBytes(), SimpleGrantedAuthority[].class));
            
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, null); // aqui no requerimos el password, ya que aqui se valida con el token
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        } catch (Exception e) {

        }
        
    }

    

}
