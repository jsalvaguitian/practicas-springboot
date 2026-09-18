package com.jesi.curso.spring.interceptors.spring_interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    @Qualifier("timeInterceptor")
    private HandlerInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //opcion 1. indicando donde queremos los interceptores
        //registry.addInterceptor(timeInterceptor).addPathPatterns("/app/test1", "/app/test3");
        //addPathPatterns("/app/**"); todas las rutas que inicie con path /app
        //opcion2 excluir en donde no queremos.
        registry.addInterceptor(timeInterceptor).excludePathPatterns("/app/test1", "/app/test3");
    }

    

}
/*
LoadingTimeInterceptor es el interceptor.
Se encarga de mirar qué pasa cuando llega una petición HTTP.
@Component:
Le dice a Spring: Creá este objeto y guardalo.

Por eso:

@Component("timeInterceptor")

crea el Bean timeInterceptor.

Pero tener @Component no alcanza.
Spring conoce el objeto.
Pero todavía no sabe que debe usarlo como interceptor de las peticiones.
MvcConfig sirve para configurar Spring MVC.

Ahí tenemos:

registry.addInterceptor(timeInterceptor);
Esto significa: Spring MVC, usá este objeto como interceptor.

Para que Spring reconozca MvcConfig, ponemos:

@Configuration

Entonces:

@Component :crea el interceptor.

@Configuration : hace que Spring reconozca la configuración.

addInterceptor() : registra el interceptor para las peticiones HTTP.

@Component > guardá este objeto"
addInterceptor() > usá este objeto como interceptor
@Configuration > reconoce la configuracion que hice en MvcConfig, porque sino no me aparecera los logs
de LoadingTimeInterceptor en la terminal, porque no se va a ejecutar el interceptor.

*/