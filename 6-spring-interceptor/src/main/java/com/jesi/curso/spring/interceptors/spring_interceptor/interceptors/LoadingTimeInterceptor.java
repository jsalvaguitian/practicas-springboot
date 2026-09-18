package com.jesi.curso.spring.interceptors.spring_interceptor.interceptors;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

@Component("timeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HandlerMethod methodController = (HandlerMethod) handler;

        logger.info("LoadingTimeInterceptor: preHandle() entrando..." + methodController.getMethod().getName());
        long start = System.currentTimeMillis();
        request.setAttribute("start", start);

        // simulacion de tarda de procesamiento
        Random random = new Random();
        int delay = random.nextInt(500);
        Thread.sleep(delay);
        //probando que el interceptor pueda bloquear la peticion, si devuelve false, 
        // no se ejecuta el handler del controlador
        Map<String, String> json  = new HashMap<>();
        json.put("error", "No tienes acceso a esta pagina");
        json.put("date",new Date().toString());

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(json);
        response.setContentType("application/json");//text/plain o text/html
        response.setStatus(401);
        response.getWriter().write(jsonString);
        return false;
        //return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {
        long end = System.currentTimeMillis();
        long start = (long) request.getAttribute("start");
        long result = end - start;
        logger.info("Tiempo transcurrido: " + result + " milisegundos.");
        logger.info(
                "LoadingTimeInterceptor: postHandler() saliendo ..." + ((HandlerMethod) handler).getMethod().getName());
    }

    // la interfaz HandlerInterceptor tiene 3 metodos que se pueden implementar,
    // preHandle, postHandle y afterCompletion
    // esos metodos no son obligatorios, se pueden implementar solo los que se
    // necesiten ademas
    // ya tienen implementaciones por defecto, por lo que no es necesario
    // implementar todos los metodos

}
