package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;
import com.example.demo.repository.CustomRepo.UserRepositoryCustom;

public interface UserRepository extends UserRepositoryCustom,JpaRepository<User, Long> {

}
