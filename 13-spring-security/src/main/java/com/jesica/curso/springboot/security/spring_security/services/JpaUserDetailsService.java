package com.jesica.curso.springboot.security.spring_security.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.jesica.curso.springboot.security.spring_security.entities.User;
import com.jesica.curso.springboot.security.spring_security.repositories.UserRepository;

public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
        Optional<User> userOptional = userRepository.findByUsername(username);

        if(userOptional.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Username %s no existe en el sistema", username));
        }
        User user = userOptional.orElseThrow();

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> (GrantedAuthority) role::getName)
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true,// accountNonExpired
                true,// accountNonLocked
                true,// credentialsNonExpired
                authorities);
    }

   

}
