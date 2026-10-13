package com.jesica.curso.springboot.security.spring_security.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.jesica.curso.springboot.security.spring_security.security.filter.JwtAuthenticationFilter;
import com.jesica.curso.springboot.security.spring_security.security.filter.JwtValidationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig {

    // Como quiero que se devuelva un componente string con referencia
    // de BCryptPasswordEncoder, lo defino como un Bean para que Spring
    // lo gestione y pueda inyectarlo en otras clases

    private final AuthenticationConfiguration authenticationConfiguration;

    SpringSecurityConfig(AuthenticationConfiguration authenticationConfiguration) {
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((authz) -> authz
                .requestMatchers(HttpMethod.GET, "/api/users").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll()
                // opcion 1 implementacion de reglas de seguridad
                // .requestMatchers(HttpMethod.POST, "/api/users").hasRole("ADMIN")
                // .requestMatchers(HttpMethod.GET,"/api/products","/api/products/{id}").hasAnyRole("ADMIN",
                // "USER")
                // .requestMatchers(HttpMethod.POST, "/api/products").hasRole("ADMIN")
                // .requestMatchers(HttpMethod.PUT, "/api/products/{id}").hasRole("ADMIN")
                // .requestMatchers(HttpMethod.DELETE, "/api/products/{id}").hasRole("ADMIN")
                // permitir el registro como publico pero no permitir el registro de un admin
                .anyRequest().authenticated())
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationManager()))
                .csrf(config -> config.disable()) 
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) //asociamos corsConfigurationSource
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    
    //Configuracion de CORS (Cross-Origin Resource Sharing o Intercambio de Recursos de Origen Cruzado) 
    // para aplicaciones cliente como angular o react u otro de JS u aplicacion Android esta configuracion se requiere
    @Bean 
    CorsConfigurationSource corsConfigurationSource(){ // se asocia tanto en filterChain como en corsfilter
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    //Este filtro de spring se ejecute siempre en esta ruta "/**" o sea en toda la app
    //Unimos el filtro con la configuracion corsConfigurationSource()
    @Bean 
    FilterRegistrationBean<CorsFilter> corsFilter(){
        FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
        corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return  corsBean;
    }
}
