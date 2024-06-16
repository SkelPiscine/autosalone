package com.example.autosalone.repository;

import com.example.autosalone.entity.users.Admin;
import com.example.autosalone.entity.users.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Admin findByEmail(String email);
}
