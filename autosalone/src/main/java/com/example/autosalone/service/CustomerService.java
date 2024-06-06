package com.example.autosalone.service;

import com.example.autosalone.entity.operations.Order;
import com.example.autosalone.entity.operations.Rental;
import com.example.autosalone.entity.users.Customer;
import com.example.autosalone.entity.vehicles.Vehicle;
import com.example.autosalone.model.OrderDTO;
import com.example.autosalone.model.PurchaseDTO;
import com.example.autosalone.model.RentalDTO;

import java.util.List;

public interface CustomerService {
    public OrderDTO orderIfAvailable(int vehicleId, int clienteId);
    List<OrderDTO> getOrders(int customerId);
    void deleteOrder(int orderId);
    public PurchaseDTO buyIfBuyable(int vehicleId);
    List<PurchaseDTO> getPurchases(int customerId);
    RentalDTO createRental();
    public List<RentalDTO> checkRentals(int customerId);
    void deleteRental(int rentalId);
    void deleteCustomerAccount(int customerId);
    Customer editProfile(Customer customer);
    Vehicle getVehicleBy();
    Vehicle getVehicleInfo(Vehicle vehicle);

}
