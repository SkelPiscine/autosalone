package com.example.autosalone.repository;

import com.example.autosalone.entity.operations.Order;
import com.example.autosalone.entity.operations.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase,Integer> {
    List<Purchase> findByCustomerId(int id);
}
