package com.onlineshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineshop.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
