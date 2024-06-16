package com.example.autosalone.repository;

import com.example.autosalone.entity.users.Customer;
import com.example.autosalone.entity.users.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,Integer> {
    Seller findByEmail(String email);
}
