package com.jesica.curso.springboot.security.spring_security.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jesica.curso.springboot.security.spring_security.entities.User;
import com.jesica.curso.springboot.security.spring_security.repositories.RoleRepository;
import com.jesica.curso.springboot.security.spring_security.repositories.UserRepository;



@Service
public class UserServiceImpl implements UserServiceI {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
       
    }

    @Override
    @Transactional 
    public User save(User user) {
        // Implementación del método save
        return userRepository.save(user);
    }

}
