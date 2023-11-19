package com.carpatotrip.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carpatotrip.web.model.UserEntity;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    UserEntity findByLastName(String name);

}
