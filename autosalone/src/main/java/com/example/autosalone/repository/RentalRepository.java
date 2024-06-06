package com.example.autosalone.repository;

import com.example.autosalone.entity.operations.Order;
import com.example.autosalone.entity.operations.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental,Integer> {
    List<Rental> findByCustomerId(int id);
}
