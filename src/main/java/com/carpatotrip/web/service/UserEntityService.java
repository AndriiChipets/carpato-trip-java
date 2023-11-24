package com.carpatotrip.web.service;

import com.carpatotrip.web.dto.RegistrationDto;
import com.carpatotrip.web.model.UserEntity;

public interface UserEntityService {
    
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

}
