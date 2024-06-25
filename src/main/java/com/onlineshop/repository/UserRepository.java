package com.onlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineshop.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
