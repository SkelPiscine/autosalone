package com.example.autosalone.service;

import com.example.autosalone.entity.operations.Order;
import com.example.autosalone.entity.operations.Rental;
import com.example.autosalone.entity.vehicles.Vehicle;
import com.example.autosalone.mapper.OrderMapper;
import com.example.autosalone.mapper.RentalMapper;
import com.example.autosalone.mapper.VehicleMapper;
import com.example.autosalone.model.OrderDTO;
import com.example.autosalone.model.RentalDTO;
import com.example.autosalone.model.VehicleDTO;
import com.example.autosalone.repository.OrderRepository;
import com.example.autosalone.repository.RentalRepository;
import com.example.autosalone.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService{

    private final VehicleMapper vehicleMapper;
    private final VehicleRepository vehicleRepository;
    private final OrderRepository orderRepository;
    private final RentalRepository rentalRepository;
    private final OrderMapper orderMapper;
    private final RentalMapper rentalMapper;

    @Override
    public VehicleDTO getVehicleInfo(int vehicleId) {
        Optional<Vehicle> vehicleTemp = vehicleRepository.findById(vehicleId);
        if (vehicleTemp.isEmpty()) {
            throw new RuntimeException("Vehicle not found at id " + vehicleId);
        }
        return vehicleMapper.toDTO(vehicleTemp.get());
    }

    @Override
    public OrderDTO createOrder(int vehicleId) {
        Optional<Vehicle> vehicleTemp = vehicleRepository.findById(vehicleId);
        if (vehicleTemp.isEmpty()) {
            throw new RuntimeException("Vehicle not found at id " + vehicleId);
        }
        Vehicle vehicle = vehicleTemp.get();
        if (!vehicle.isOrdinabile()) {
            throw new RuntimeException("Vehicle not available for ordering");
        }
        Order newOrder = new Order().builder().vehicle(vehicle).build();
        newOrder = orderRepository.save(newOrder);
        return orderMapper.toDTO(newOrder);
    }

    @Override
    public void deleteOrder(int orderId) {
        Optional<Order> orderTemp = orderRepository.findById(orderId);
        if (orderTemp.isEmpty()) {
            throw new RuntimeException("Order not found at id: " + orderId);
        }
        orderRepository.deleteById(orderId);
    }

    @Override
    public OrderDTO modifyOrder(int orderId, OrderDTO newOrder) {
        Optional<Order> orderTemp = orderRepository.findById(orderId);
        if (orderTemp.isEmpty()) {
            throw new RuntimeException("Order not found at id: " + orderId);
        }
        Order order = orderTemp.get();
        if (!newOrder.getStatoOrdine().isEmpty()) {
            order.setStatoOrdine(newOrder.getStatoOrdine());
        }
        orderRepository.save(order);
        return orderMapper.toDTO(order);
    }


    @Override
    public String getOrderStatus(int orderId) {
        Optional<Order> orderTemp = orderRepository.findById(orderId);
        if (orderTemp.isEmpty()) {
            throw new RuntimeException("Order not found at id: " + orderId);
        }
        Order order = orderTemp.get();
        return order.getStatoOrdine();
    }

    @Override
    public OrderDTO updateOrderStatus(int orderId, String status) {
        Optional<Order> orderTemp = orderRepository.findById(orderId);
        if (orderTemp.isEmpty()) {
            throw new RuntimeException("Order not found at id: " + orderId);
        }
        Order order = orderTemp.get();
        order.setStatoOrdine(status);
        Order updatedOrder = orderRepository.save(order);
        return orderMapper.toDTO(updatedOrder);
    }
    @Override
    public List<OrderDTO> getOrdersByStatus(String status) {
        List<Order> orders = orderRepository.findByStatoOrdine(status);
        return orderMapper.toDTOList(orders);
    }

    @Override
    public RentalDTO createRental(LocalDate dataInizio, LocalDate dataFine,
                                  double costoGiornaliero, int vehicleId) {
        Optional<Vehicle> vehicleTemp = vehicleRepository.findById(vehicleId);
        if(vehicleTemp.isEmpty()){
            throw new RuntimeException("no vehicle found at id " + vehicleId);
        }
        Vehicle vehicle = vehicleTemp.get();
        if (!vehicle.isDisponibile())
        {
            throw new RuntimeException("vehicle not available");
        }
        vehicle.setDisponibile(false);
        vehicleRepository.save(vehicle);
        double durataNoleggio = TimeUnit.DAYS.convert(Duration.between(dataInizio, dataFine));
        double costoTotale = (durataNoleggio + 1) * costoGiornaliero;
        Rental rental = new Rental().builder()
                .veicolo(vehicle)
                .dataInizio(dataInizio)
                .dataFine(dataFine)
                .costoGiornaliero(costoGiornaliero)
                .costoTotale(costoTotale)
                .build();
        rentalRepository.save(rental);
        return rentalMapper.toDTO(rental);
    }

    @Override
    public void deleteRental(int rentalId) {
        Optional<Rental> rentalTemp = rentalRepository.findById(rentalId);
        if (rentalTemp.isEmpty()) {
            throw new RuntimeException("Rental not found at id: " + rentalId);
        }
        rentalRepository.deleteById(rentalId);
    }

    @Override
    public RentalDTO modifyRental(int rentalId, RentalDTO newRental) {
        Optional<Rental> rentalTemp = rentalRepository.findById(rentalId);
        if (rentalTemp.isEmpty()) {
            throw new RuntimeException("Rental not found at id: " + rentalId);
        }
        Rental rental = rentalTemp.get();
        if (!newRental.getDataInizio().isEqual(rental.getDataInizio())) {
            rental.setDataInizio(newRental.getDataInizio());
        }
        if (newRental.getDataFine().isEqual(rental.getDataFine())) {
            rental.setDataFine(newRental.getDataFine());
        }
        rentalRepository.save(rental);
        return rentalMapper.toDTO(rental);
    }
}
