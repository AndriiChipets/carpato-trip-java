package com.carpatotrip.web.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.carpatotrip.web.dto.RegistrationDto;
import com.carpatotrip.web.model.Role;
import com.carpatotrip.web.model.UserEntity;
import com.carpatotrip.web.repository.RoleRepository;
import com.carpatotrip.web.repository.UserEntityRepository;
import com.carpatotrip.web.service.UserEntityService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserEntityServiceImpl implements UserEntityService {
    
    private final UserEntityRepository userEntityRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(RegistrationDto registrationDto) {
       
        String roleName = "USER";
        Role role = roleRepository.findByName(roleName);
        Set<Role> roles = new HashSet<>(Arrays.asList(role));
        UserEntity user = UserEntity.builder()
                .withFirstName(registrationDto.getFirstName())
                .withLastName(registrationDto.getLastName())
                .withEmail(registrationDto.getEmail())
                .withPassword(passwordEncoder.encode(registrationDto.getPassword()))
                .withRoles(roles)
                .build();
        userEntityRepository.save(user);   
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userEntityRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByLastName(String lastName) {
        return userEntityRepository.findByLastName(lastName);
    }

}
