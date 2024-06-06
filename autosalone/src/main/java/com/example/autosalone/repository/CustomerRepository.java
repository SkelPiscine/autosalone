package com.example.autosalone.repository;

import com.example.autosalone.entity.users.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
