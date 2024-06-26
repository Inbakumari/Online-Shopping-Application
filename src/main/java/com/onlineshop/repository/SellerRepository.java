package com.onlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineshop.entity.Seller;

public interface SellerRepository extends JpaRepository<Seller, Integer> {

}
