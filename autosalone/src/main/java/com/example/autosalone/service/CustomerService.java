package com.example.autosalone.service;

import com.example.autosalone.entity.operations.Order;
import com.example.autosalone.entity.operations.Rental;
import com.example.autosalone.entity.users.Customer;
import com.example.autosalone.entity.vehicles.Vehicle;
import com.example.autosalone.model.*;

import java.time.LocalDate;
import java.util.List;

public interface CustomerService {
    public OrderDTO orderIfAvailable(int vehicleId, int clienteId);
    List<OrderDTO> getOrders(int customerId);
    void deleteOrder(int orderId);
    public PurchaseDTO buyIfBuyable(int vehicleId);
    List<PurchaseDTO> getPurchases(int customerId);
    public RentalDTO createRental(LocalDate dataInizio, LocalDate dataFine,
                                  double costoGiornaliero, int vehicleId);
    public List<RentalDTO> checkRentals(Customer customer);
    void deleteRental(int rentalId);
    void deleteCustomerAccount(int customerId);
    public CustomerDTO editProfile(CustomerDTO customerInfo, int customerId);
    List<VehicleDTO> findByMarca(String marca);
    List<VehicleDTO> findByModello(String modello);
    List<VehicleDTO> findByColore(String colore);
    List<VehicleDTO> findByCilindrata(int cilindrata);
    List<VehicleDTO> findByCambio(String cambio);
    VehicleDTO getVehicleInfo(int vehicleId);
}
