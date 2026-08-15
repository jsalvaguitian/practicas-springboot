package com.jesi.curso.spring.interceptors.spring_interceptor.interceptors;

import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("timeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class); 

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {
        logger.info("LoadingTimeInterceptor: postHandler() saliendo ..." + ((HandlerMethod) handler).getMethod().getName());
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        logger.info("LoadingTimeInterceptor: preHandle() entrando..." + ((HandlerMethod) handler).getMethod().getName());
        return true;
    }
//la interfaz HandlerInterceptor tiene 3 metodos que se pueden implementar, preHandle, postHandle y afterCompletion
//esos metodos no son obligatorios, se pueden implementar solo los que se necesiten ademas 
// ya tienen implementaciones por defecto, por lo que no es necesario implementar todos los metodos


}
