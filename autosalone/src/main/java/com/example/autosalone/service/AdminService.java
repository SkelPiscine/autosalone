package com.example.autosalone.service;

import com.example.autosalone.model.*;

import java.time.LocalDate;
import java.util.List;

public interface AdminService {
    public VehicleDTO addVehicle(VehicleDTO vehicleDto) throws Exception;
    public VehicleDTO updateVehicle(int vehicleId, VehicleDTO newVehicleDto) throws Exception;
    public void deleteVehicle(int vehicleId);
    public OrderDTO createOrderForUser(OrderDTO orderDto, int customerId) throws Exception;
    public void deleteOrderForUser(int orderId) throws Exception;
    public OrderDTO modifyOrderForUser(int orderId, OrderDTO orderDTO) throws Exception;
    public RentalDTO createRental(LocalDate dataInizio, LocalDate dataFine,
                                  double costoGiornaliero, int vehicleId);
    public void deleteRental(int rentalId);
    public RentalDTO modifyRental(int rentalId, RentalDTO newRental);
    public PurchaseDTO createPurchaseForUser(PurchaseDTO purchaseDto, int customerId) throws Exception;
    public void deletePurchaseForUser(int purchaseId) throws Exception;
    public PurchaseDTO modifyPurchaseForUser(int purchaseId, PurchaseDTO updatedPurchaseDto) throws Exception;
    public List<VehicleDTO> getOrdinabileVehicles();
    public List<VehicleDTO> getAcquistabileVehicles();
    public List<VehicleDTO> getNuovoVehicles();
    public List<VehicleDTO> getUsatoVehicles();
    public void deleteCustomer(int customerId) throws Exception;
    public CustomerDTO modifyCustomer(int customerId, CustomerDTO customerDTO) throws Exception;
    public void deleteSeller(int sellerId) throws Exception;
    public SellerDTO modifySeller(int sellerId, SellerDTO updatedSellerDto) throws Exception;
}
