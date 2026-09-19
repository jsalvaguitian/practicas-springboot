package com.jesica.curso.springboot.calendar.interceptor.springboot_interceptor_horario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //@Configuration: Indica que esta clase es de configuración y se debe registrar en el contenedor de spring
public class MvcConfig implements WebMvcConfigurer{

    @Qualifier("calendarInterceptor")
    private final HandlerInterceptor calendar;

    //No se requiere el @Autowired porque solo hay un constructor, es opcional desde spring version 4, 
    // pero se puede colocar para indicar que es un constructor de inyección de dependencias
    MvcConfig(HandlerInterceptor calendar) {
        this.calendar = calendar;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(calendar).addPathPatterns("/test1");
    }

}
