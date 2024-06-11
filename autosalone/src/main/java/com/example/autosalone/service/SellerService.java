package com.example.autosalone.service;

import com.example.autosalone.model.OrderDTO;
import com.example.autosalone.model.RentalDTO;
import com.example.autosalone.model.VehicleDTO;

import java.time.LocalDate;
import java.util.List;

public interface SellerService {
    public VehicleDTO getVehicleInfo(int vehicleId);
    public OrderDTO createOrder(int vehicleId);
    public void deleteOrder(int orderId);
    public OrderDTO modifyOrder(int orderId, OrderDTO modifiedOrderDTO);
    public String getOrderStatus(int orderId);
    public OrderDTO updateOrderStatus(int orderId, String status);
    public List<OrderDTO> getOrdersByStatus(String status);
    public RentalDTO createRental(LocalDate dataInizio, LocalDate dataFine,
                                  double costoGiornaliero, int vehicleId);
    public void deleteRental(int rentalId);
    public RentalDTO modifyRental(int rentalId, RentalDTO modifiedRentalDTO);
}
