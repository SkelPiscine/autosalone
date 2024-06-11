package com.example.autosalone.service;

import com.example.autosalone.mapper.OrderMapper;
import com.example.autosalone.mapper.RentalMapper;
import com.example.autosalone.mapper.VehicleMapper;
import com.example.autosalone.repository.OrderRepository;
import com.example.autosalone.repository.RentalRepository;
import com.example.autosalone.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final VehicleRepository vehicleRepository;
    private final OrderRepository orderRepository;
    private final RentalRepository rentalRepository;
    private final VehicleMapper vehicleMapper;
    private final OrderMapper orderMapper;
    private final RentalMapper rentalMapper;

}
