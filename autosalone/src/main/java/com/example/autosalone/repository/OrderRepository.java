package com.example.autosalone.repository;

import com.example.autosalone.entity.operations.Order;
import com.example.autosalone.entity.operations.Purchase;
import com.example.autosalone.entity.users.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findByCustomerId(int id);
    List<Order> findByStatoOrdine(String stato);
}
