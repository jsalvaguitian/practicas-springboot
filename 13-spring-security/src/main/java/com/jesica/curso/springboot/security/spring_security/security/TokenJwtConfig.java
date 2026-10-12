package com.jesica.curso.springboot.security.spring_security.security;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;

public class TokenJwtConfig {

    //Esta clase de configuracion va tener las constantes que usa el JWTAuthenticationFilter al importar esta clase

    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build(); // Generamos de modo automatica la secret
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization"; // key, ni yo la sabre xD ja. Y solo se
    public static final String CONTENT_TYPE = "application/json";
}
