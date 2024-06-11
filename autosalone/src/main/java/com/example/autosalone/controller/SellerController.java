package com.example.autosalone.controller;

import com.example.autosalone.model.OrderDTO;
import com.example.autosalone.model.RentalDTO;
import com.example.autosalone.model.VehicleDTO;
import com.example.autosalone.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seller")
public class SellerController {
    private final SellerService sellerService;

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<VehicleDTO> getVehicleInfo(@PathVariable int vehicleId) {
        try {
            VehicleDTO vehicle = sellerService.getVehicleInfo(vehicleId);
            return ResponseEntity.ok(vehicle);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/vehicle/{vehicleId}/order")
    public ResponseEntity<OrderDTO> createOrder(@PathVariable int vehicleId) {
        try {
            OrderDTO order = sellerService.createOrder(vehicleId);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int orderId) {
        try {
            sellerService.deleteOrder(orderId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/order/{orderId}")
    public ResponseEntity<OrderDTO> modifyOrder(@PathVariable int orderId, @RequestBody OrderDTO newOrder) {
        try {
            OrderDTO modifiedOrder = sellerService.modifyOrder(orderId, newOrder);
            return ResponseEntity.ok(modifiedOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/order/{orderId}/status")
    public ResponseEntity<String> getOrderStatus(@PathVariable int orderId) {
        try {
            String status = sellerService.getOrderStatus(orderId);
            return ResponseEntity.ok(status);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/order/{orderId}/status")
    public ResponseEntity<OrderDTO> updateOrderStatus(@PathVariable int orderId, @RequestParam String status) {
        try {
            OrderDTO updatedOrder = sellerService.updateOrderStatus(orderId, status);
            return ResponseEntity.ok(updatedOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getOrdersByStatus(@RequestParam String status) {
        try {
            List<OrderDTO> orders = sellerService.getOrdersByStatus(status);
            return ResponseEntity.ok(orders);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/vehicle/{vehicleId}/rental")
    public ResponseEntity<RentalDTO> createRental(@RequestParam LocalDate dataInizio,
                                                  @RequestParam LocalDate dataFine,
                                                  @RequestParam double costoGiornaliero,
                                                  @PathVariable int vehicleId) {
        try {
            RentalDTO rental = sellerService.createRental(dataInizio, dataFine, costoGiornaliero, vehicleId);
            return ResponseEntity.ok(rental);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/rental/{rentalId}")
    public ResponseEntity<Void> deleteRental(@PathVariable int rentalId) {
        try {
            sellerService.deleteRental(rentalId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/rental/{rentalId}")
    public ResponseEntity<RentalDTO> modifyRental(@PathVariable int rentalId, @RequestBody RentalDTO newRental) {
        try {
            RentalDTO modifiedRental = sellerService.modifyRental(rentalId, newRental);
            return ResponseEntity.ok(modifiedRental);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

