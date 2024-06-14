package com.example.autosalone.controller;

import com.example.autosalone.entity.users.Customer;
import com.example.autosalone.model.*;
import com.example.autosalone.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/{customerId}/order/{vehicleId}")
    public ResponseEntity<OrderDTO> orderIfAvailable(@PathVariable int customerId, @PathVariable int vehicleId) {
        try {
            OrderDTO order = customerService.orderIfAvailable(vehicleId, customerId);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{customerId}/orders")
    public ResponseEntity<List<OrderDTO>> getOrders(@PathVariable int customerId) {
        try {
            List<OrderDTO> orders = customerService.getOrders(customerId);
            return ResponseEntity.ok(orders);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int orderId) {
        try {
            customerService.deleteOrder(orderId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/vehicle/{vehicleId}/purchase")
    public ResponseEntity<PurchaseDTO> buyIfBuyable(@PathVariable int vehicleId) {
        try {
            PurchaseDTO purchase = customerService.buyIfBuyable(vehicleId);
            return ResponseEntity.ok(purchase);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{customerId}/purchases")
    public ResponseEntity<List<PurchaseDTO>> getPurchases(@PathVariable int customerId) {
        try {
            List<PurchaseDTO> purchases = customerService.getPurchases(customerId);
            return ResponseEntity.ok(purchases);
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
            RentalDTO rental = customerService.createRental(dataInizio, dataFine, costoGiornaliero, vehicleId);
            return ResponseEntity.ok(rental);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{customerId}/rentals")
    public ResponseEntity<List<RentalDTO>> checkRentals(@RequestBody Customer customer) {
        try {
            List<RentalDTO> rentals = customerService.checkRentals(customer);
            return ResponseEntity.ok(rentals);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/rental/{rentalId}")
    public ResponseEntity<Void> deleteRental(@PathVariable int rentalId) {
        try {
            customerService.deleteRental(rentalId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomerAccount(@PathVariable int customerId) {
        try {
            customerService.deleteCustomerAccount(customerId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> editProfile(@RequestBody CustomerDTO customerInfo,
                                                   @PathVariable int customerId) {
        try {
            CustomerDTO updatedCustomer = customerService.editProfile(customerInfo, customerId);
            return ResponseEntity.ok(updatedCustomer);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/vehicles/marca")
    public ResponseEntity<List<VehicleDTO>> findByMarca(@RequestParam String marca) {
        try {
            List<VehicleDTO> vehicles = customerService.findByMarca(marca);
            return ResponseEntity.ok(vehicles);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/vehicles/modello")
    public ResponseEntity<List<VehicleDTO>> findByModello(@RequestParam String modello) {
        try {
            List<VehicleDTO> vehicles = customerService.findByModello(modello);
            return ResponseEntity.ok(vehicles);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/vehicles/colore")
    public ResponseEntity<List<VehicleDTO>> findByColore(@RequestParam String colore) {
        try {
            List<VehicleDTO> vehicles = customerService.findByColore(colore);
            return ResponseEntity.ok(vehicles);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/vehicles/cilindrata")
    public ResponseEntity<List<VehicleDTO>> findByCilindrata(@RequestParam int cilindrata) {
        try {
            List<VehicleDTO> vehicles = customerService.findByCilindrata(cilindrata);
            return ResponseEntity.ok(vehicles);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/vehicles/cambio")
    public ResponseEntity<List<VehicleDTO>> findByCambio(@RequestParam String cambio) {
        try {
            List<VehicleDTO> vehicles = customerService.findByCambio(cambio);
            return ResponseEntity.ok(vehicles);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<VehicleDTO> getVehicleInfo(@PathVariable int vehicleId) {
        try {
            VehicleDTO vehicle = customerService.getVehicleInfo(vehicleId);
            return ResponseEntity.ok(vehicle);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
