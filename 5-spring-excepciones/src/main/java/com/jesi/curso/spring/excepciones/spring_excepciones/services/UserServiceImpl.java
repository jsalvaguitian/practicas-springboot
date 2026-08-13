package com.jesi.curso.spring.excepciones.spring_excepciones.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jesi.curso.spring.excepciones.spring_excepciones.models.domain.User;

@Service
public class UserServiceImpl implements UserService {
    
    //Lo cambiaremos a una clase Config para que sea un Bean y podamos inyectarlo en el controlador
    @Autowired
    private List<User> users;

    
    // public UserServiceImpl() {
    //     this.users = new ArrayList<>();

    //     users.add(new User(1L, "Jesica", "Salva"));
    //     users.add(new User(2L, "Marge", "Simpson"));
    //     users.add(new User(3L, "Jazmin", "Gonzalez"));
    //     users.add(new User(4L, "Erica", "Benitez"));
    //     users.add(new User(5L, "Belen", "Guitian"));

    // }

    @Override
    public List<User> findAll() {
        return users;
    }

    //Opcion 2 aplicando Api Stream. como optimizar el codigo y corto. Se puede usar el metodo findFirst() 
    // para obtener el primer elemento que cumpla la condicion. Si no encuentra ninguno, devuelve un Optional vacio.
    @Override
    public Optional<User> findById(Long id) {
        return users.stream().filter(u -> u.getId().equals(id)).findFirst();
    }

    // @Override
    // public Optional<User> findById(Long id) {
    //     User user = null;

    //     for(User u : this.users){
    //         if(u.getId().equals(id)){
    //             user = u;
    //             break;
    //         }
    //     }
    //     //Opcion 1 modo resumido
    //     return Optional.ofNullable(user);

    //     Opcion 2 modo largo
    //     if(user == null){
    //         return Optional.empty();
    //     }else{
    //         return Optional.of(user);
    //     }
        
    // }


}
