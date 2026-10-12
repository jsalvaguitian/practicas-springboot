package com.jesica.curso.springboot.security.spring_security.security.filter;

import static com.jesica.curso.springboot.security.spring_security.security.TokenJwtConfig.CONTENT_TYPE;
import static com.jesica.curso.springboot.security.spring_security.security.TokenJwtConfig.HEADER_AUTHORIZATION;
import static com.jesica.curso.springboot.security.spring_security.security.TokenJwtConfig.PREFIX_TOKEN;
import static com.jesica.curso.springboot.security.spring_security.security.TokenJwtConfig.SECRET_KEY;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

public class JwtValidationFilter extends BasicAuthenticationFilter {

    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader(HEADER_AUTHORIZATION);

        if (header == null || !header.startsWith(PREFIX_TOKEN)) {
            chain.doFilter(request, response);// en caso si la pagina o el recurso publico que no esta protegido
                                              // igualmente tiene que seguir con la secuencia
            return;
        }

        String token = header.replace(PREFIX_TOKEN, "");

        try {// manejamos el erron en caso si pasa la fecha de validez del token o token
             // erroneo

            // Guiarse con la documentacion del repositorio JJWT porque las cosas cambian
            Claims claims = Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();

            // Como obtenemos el username que vimos en JwtAutenticationFilter
            String usernameDesdeSubject = claims.getSubject();
            //String usernameDesdeClaims = (String) claims.get("username");

            /*
             * Object authoritiesClaims = claims.get("authorities");
             * 
             * Collection<? extends GrantedAuthority> authorities = Arrays.asList(
             * new ObjectMapper().addMixIn(SimpleGrantedAuthority.class,
             * SimpleGrantedAuthorityJsonCreator.class)
             * .readValue(
             * authoritiesClaims.toString().getBytes(),
             * SimpleGrantedAuthority[].class)
             * );
             */

            Object authoritiesClaims = claims.get("authorities");

            if (!(authoritiesClaims instanceof Collection<?> authorityNames)) {
                throw new JwtException("Claim authorities inválido");
            }

            Collection<GrantedAuthority> authorities = new ArrayList<>();

            for (Object authorityName : authorityNames) {
                if (!(authorityName instanceof String name)) {
                    throw new JwtException("Nombre de autoridad inválido");
                }

                authorities.add(new SimpleGrantedAuthority(name));
            }

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    usernameDesdeSubject, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);

        } catch (JwtException e) {
            Map<String, String> body = new HashMap<>();
            body.put("error", e.getMessage());
            body.put("message", "El token JWT es invalido");

            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(CONTENT_TYPE);

        }

    }

}
