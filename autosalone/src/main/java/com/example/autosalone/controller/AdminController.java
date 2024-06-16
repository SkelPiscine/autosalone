package com.example.autosalone.controller;

import com.example.autosalone.model.*;
import com.example.autosalone.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/vehicle")
    public ResponseEntity<VehicleDTO> addVehicle(@RequestBody VehicleDTO vehicleDto) {
        try {
            VehicleDTO addedVehicle = adminService.addVehicle(vehicleDto);
            return ResponseEntity.ok(addedVehicle);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/vehicle/{vehicleId}")
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable int vehicleId, @RequestBody VehicleDTO newVehicleDto) {
        try {
            VehicleDTO updatedVehicle = adminService.updateVehicle(vehicleId, newVehicleDto);
            return ResponseEntity.ok(updatedVehicle);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/vehicle/{vehicleId}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable int vehicleId) {
        try {
            adminService.deleteVehicle(vehicleId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/order")
    public ResponseEntity<OrderDTO> createOrderForUser(@RequestBody OrderDTO orderDto,
                                                       @RequestParam int customerId) {
        try {
            OrderDTO createdOrder = adminService.createOrderForUser(orderDto, customerId);
            return ResponseEntity.ok(createdOrder);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<Void> deleteOrderForUser(@PathVariable int orderId) {
        try {
            adminService.deleteOrderForUser(orderId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/order/{orderId}")
    public ResponseEntity<OrderDTO> modifyOrderForUser(@PathVariable int orderId,
                                                       @RequestBody OrderDTO orderDto) {
        try {
            OrderDTO modifiedOrder = adminService.modifyOrderForUser(orderId, orderDto);
            return ResponseEntity.ok(modifiedOrder);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/rental")
    public ResponseEntity<RentalDTO> createRental(@RequestParam LocalDate dataInizio,
                                                  @RequestParam LocalDate dataFine,
                                                  @RequestParam double costoGiornaliero,
                                                  @RequestParam int vehicleId) {
        try {
            RentalDTO createdRental = adminService.createRental(dataInizio, dataFine, costoGiornaliero, vehicleId);
            return ResponseEntity.ok(createdRental);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/rental/{rentalId}")
    public ResponseEntity<Void> deleteRental(@PathVariable int rentalId) {
        try {
            adminService.deleteRental(rentalId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/rental/{rentalId}")
    public ResponseEntity<RentalDTO> modifyRental(@PathVariable int rentalId,
                                                  @RequestBody RentalDTO newRental) {
        try {
            RentalDTO modifiedRental = adminService.modifyRental(rentalId, newRental);
            return ResponseEntity.ok(modifiedRental);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/purchase/{customerId}")
    public ResponseEntity<PurchaseDTO> createPurchaseForUser(@RequestBody PurchaseDTO purchaseDto,
                                                             @PathVariable int customerId) {
        try {
            PurchaseDTO createdPurchase = adminService.createPurchaseForUser(purchaseDto, customerId);
            return ResponseEntity.ok(createdPurchase);
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // Potresti voler gestire diversamente l'eccezione
        }
    }

    @DeleteMapping("/purchase/{purchaseId}")
    public ResponseEntity<Void> deletePurchaseForUser(@PathVariable int purchaseId) {
        try {
            adminService.deletePurchaseForUser(purchaseId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // Potresti voler gestire diversamente l'eccezione
        }
    }

    @PutMapping("/purchase/{purchaseId}")
    public ResponseEntity<PurchaseDTO> modifyPurchaseForUser(@PathVariable int purchaseId,
                                                             @RequestBody PurchaseDTO updatedPurchaseDto) {
        try {
            PurchaseDTO modifiedPurchase = adminService.modifyPurchaseForUser(purchaseId, updatedPurchaseDto);
            return ResponseEntity.ok(modifiedPurchase);
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // Potresti voler gestire diversamente l'eccezione
        }
    }

    @GetMapping("/vehicles/ordinabile")
    public ResponseEntity<List<VehicleDTO>> getOrdinabileVehicles() {
        try {
            List<VehicleDTO> ordinabileVehicles = adminService.getOrdinabileVehicles();
            return ResponseEntity.ok(ordinabileVehicles);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/vehicles/acquistabile")
    public ResponseEntity<List<VehicleDTO>> getAcquistabileVehicles() {
        try {
            List<VehicleDTO> acquistabileVehicles = adminService.getAcquistabileVehicles();
            return ResponseEntity.ok(acquistabileVehicles);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/vehicles/nuovo")
    public ResponseEntity<List<VehicleDTO>> getNuovoVehicles() {
        try {
            List<VehicleDTO> nuovoVehicles = adminService.getNuovoVehicles();
            return ResponseEntity.ok(nuovoVehicles);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/vehicles/usato")
    public ResponseEntity<List<VehicleDTO>> getUsatoVehicles() {
        try {
            List<VehicleDTO> usatoVehicles = adminService.getUsatoVehicles();
            return ResponseEntity.ok(usatoVehicles);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int customerId) {
        try {
            adminService.deleteCustomer(customerId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/customer/{customerId}")
    public ResponseEntity<CustomerDTO> modifyCustomer(@PathVariable int customerId,
                                                      @RequestBody CustomerDTO customerDto) {
        try {
            CustomerDTO modifiedCustomer = adminService.modifyCustomer(customerId, customerDto);
            return ResponseEntity.ok(modifiedCustomer);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/seller/{sellerId}")
    public ResponseEntity<Void> deleteSeller(@PathVariable int sellerId) {
        try {
            adminService.deleteSeller(sellerId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/seller/{sellerId}")
    public ResponseEntity<SellerDTO> modifySeller(@PathVariable int sellerId,
                                                  @RequestBody SellerDTO sellerDto) {
        try {
            SellerDTO modifiedSeller = adminService.modifySeller(sellerId, sellerDto);
            return ResponseEntity.ok(modifiedSeller);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
