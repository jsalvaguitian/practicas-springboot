package com.example.spring.crud.security.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.crud.security.spring.entities.Role;
import com.example.spring.crud.security.spring.entities.User;
import com.example.spring.crud.security.spring.repositories.RoleRepository;
import com.example.spring.crud.security.spring.repositories.UserRepository;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {//agregar rol antes de persistir
        

        Optional<Role>optionalRoleUser= roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();

        optionalRoleUser.ifPresent(roles::add);//es lo mismo (role -> roles.add(role))

        if(user.isAdmin()){
            Optional<Role> optionalRoleAdmin= roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }

        user.setRoles(roles);
        String passwordEncoded = passwordEncoder.encode(user.getPassword());//encriptamos la contrasenia antes de persistir los datos en la bbd
        user.setPassword(passwordEncoded);
        return repository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

}
