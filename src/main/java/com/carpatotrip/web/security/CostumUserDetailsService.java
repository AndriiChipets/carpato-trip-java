package com.carpatotrip.web.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.carpatotrip.web.model.UserEntity;
import com.carpatotrip.web.repository.UserEntityRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CostumUserDetailsService implements UserDetailsService {
    
    private UserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        UserEntity user = userEntityRepository.findByEmail(userEmail);
        if(user != null) {
            User authUser = new User(
                    userEmail,
                    user.getPassword(),
                    user.getRoles().stream().map((role)-> new SimpleGrantedAuthority(role.getName())).toList()
                    ); 
            return authUser;
        } else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}
