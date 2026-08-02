package com.jesi.curso.spring.excepciones.spring_excepciones.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jesi.curso.spring.excepciones.spring_excepciones.models.domain.User;

@Service
public class UserServiceImpl implements UserService {
    private List<User> users;

    
    public UserServiceImpl() {
        this.users = new ArrayList<>();

        users.add(new User(1L, "Jesica", "Salva"));
        users.add(new User(2L, "Marge", "Simpson"));
        users.add(new User(3L, "Jazmin", "Gonzalez"));
        users.add(new User(4L, "Erica", "Benitez"));
        users.add(new User(5L, "Belen", "Guitian"));

    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findById(Long id) {
        User user = null;

        for(User u : users){
            if(u.getId().equals(id)){
                user = u;
                break;
            }
        }
        return user;
        
    }

    

    
}
