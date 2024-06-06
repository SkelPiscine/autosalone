package com.example.autosalone.service;

import com.example.autosalone.entity.operations.Order;
import com.example.autosalone.entity.operations.Purchase;
import com.example.autosalone.entity.operations.Rental;
import com.example.autosalone.entity.users.Customer;
import com.example.autosalone.entity.vehicles.Vehicle;
import com.example.autosalone.mapper.OrderMapper;
import com.example.autosalone.mapper.PurchaseMapper;
import com.example.autosalone.mapper.RentalMapper;
import com.example.autosalone.mapper.VehicleMapper;
import com.example.autosalone.model.OrderDTO;
import com.example.autosalone.model.PurchaseDTO;
import com.example.autosalone.model.RentalDTO;
import com.example.autosalone.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final VehicleRepository vehicleRepository;
    private final CustomerRepository customerRepository;
    private final RentalMapper rentalMapper;
    private final RentalRepository rentalRepository;
    private final PurchaseMapper purchaseMapper;
    private final PurchaseRepository purchaseRepository;
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    @Override
    public OrderDTO orderIfAvailable(int vehicleId, int clienteId) {
        Optional<Vehicle> vehicleTemp = vehicleRepository.findById(vehicleId);
        Optional<Customer> customerTemp = customerRepository.findById(clienteId);
        if(vehicleTemp.isEmpty()){
            throw new RuntimeException("no vehicle found at id " + vehicleId);
        }
        if(customerTemp.isEmpty()){
            throw new RuntimeException("no customer found at id " + clienteId);
        }
        Vehicle vehicle = vehicleTemp.get();
        if (!vehicle.isOrdinabile())
        {
            throw new RuntimeException("vehicle not available");
        }
        Customer customer = customerTemp.get();
        Order newOrder = new Order().builder().veicolo(vehicle).cliente(customer).build();
        newOrder = orderRepository.save(newOrder);
        vehicle.setOrdinabile(false);
        vehicleRepository.save(vehicle);
        return orderMapper.toDTO(newOrder);
    }

    @Override
    public List<OrderDTO> getOrders(int customerId) {
        Optional<Customer> customerTemp = customerRepository.findById(customerId);
        if (customerTemp.isEmpty()){
            throw new RuntimeException("customer not found at id " + customerId);
        }
        List<Order> orderList = orderRepository.findByCustomerId(customerId);
        return orderMapper.toDTOList(orderList);
    }

    @Override
    public void deleteOrder(int orderId) {
        Optional<Order> orderTemp = orderRepository.findById(orderId);
        if (orderTemp.isEmpty()){
            throw new RuntimeException("order not found at id: " + orderId);
        }
        orderRepository.deleteById(orderId);
    }

    @Override
    public PurchaseDTO buyIfBuyable(int vehicleId) {
        Optional<Vehicle> vehicleTemp = vehicleRepository.findById(vehicleId);
        if(vehicleTemp.isEmpty()){
            throw new RuntimeException("no vehicle found at id " + vehicleId);
        }
        Vehicle vehicle = vehicleTemp.get();
        if (vehicle.isDisponibile())
        {
            throw new RuntimeException("vehicle not available");
        }
        Purchase newPurchase = new Purchase().builder().veicolo(vehicle).build();
        newPurchase = purchaseRepository.save(newPurchase);
        vehicle.setDisponibile(false);
        vehicleRepository.save(vehicle);
        return purchaseMapper.toDTO(newPurchase);
    }

    @Override
    public List<PurchaseDTO> getPurchases(int customerId) {
        Optional<Customer> customerTemp = customerRepository.findById(customerId);
        if (customerTemp.isEmpty()){
            throw new RuntimeException("customer not found at id " + customerId);
        }
        List<Purchase> purchaseList = purchaseRepository.findByCustomerId(customerId);
        return purchaseMapper.toDTOList(purchaseList);
    }

    @Override
    public RentalDTO createRental() {
        return null;
    }

    @Override
    public List<RentalDTO> checkRentals(int customerId) {
        Optional<Customer> customerTemp = customerRepository.findById(customerId);
        if (customerTemp.isEmpty()){
            throw new RuntimeException("customer not found at id " + customerId);
        }
        List<Rental> rentalList = rentalRepository.findByCustomerId(customerId);
        return rentalMapper.toDTOList(rentalList);
    }

    @Override
    public void deleteRental(int rentalId) {
        Optional<Rental> rentalTemp = rentalRepository.findById(rentalId);
        if (rentalTemp.isEmpty()){
            throw new RuntimeException("order not found at id: " + rentalId);
        }
        rentalRepository.deleteById(rentalId);

    }

    @Override
    public void deleteCustomerAccount(int customerId) {
        Optional<Customer> customerTemp = customerRepository.findById(customerId);
        if (customerTemp.isEmpty()){
            throw new RuntimeException("customer not found at id " + customerId);
        }
        customerRepository.deleteById(customerId);
    }

    @Override
    public Customer editProfile(Customer customer) {
        return null;
    }

    @Override
    public Vehicle getVehicleBy() {
        return null;
    }

    @Override
    public Vehicle getVehicleInfo(Vehicle vehicle) {
        return null;
    }
}
