package com.example.autosalone.service;

import com.example.autosalone.entity.operations.Order;
import com.example.autosalone.entity.operations.Purchase;
import com.example.autosalone.entity.operations.Rental;
import com.example.autosalone.entity.users.Customer;
import com.example.autosalone.entity.vehicles.Vehicle;
import com.example.autosalone.mapper.*;
import com.example.autosalone.model.*;
import com.example.autosalone.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final VehicleMapper vehicleMapper;
    private final VehicleRepository vehicleRepository;
    private final CustomerMapper customerMapper;
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
        Order newOrder = new Order().builder().vehicle(vehicle).customer(customer).build();
        newOrder = orderRepository.save(newOrder);
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
        if (!vehicle.isDisponibile())
        {
            throw new RuntimeException("vehicle not available");
        }
        Purchase newPurchase = new Purchase().builder().vehicle(vehicle).build();
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
    public List<RentalDTO> checkRentals(Customer customer) {
        Optional<Customer> customerTemp = customerRepository.findById(customer.getId());
        if (customerTemp.isEmpty()){
            throw new RuntimeException("customer not found at id " + customer);
        }
        List<Rental> rentalList = rentalRepository.findByCustomer(customer);
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
    public CustomerDTO editProfile(CustomerDTO customerInfo, int customerId) {
        Optional<Customer> customerTemp = customerRepository.findById(customerId);
        if (customerTemp.isEmpty()){
            throw new RuntimeException("customer not found at id " + customerId);
        }
        Customer customerUpdated = customerTemp.get();
        if (!customerInfo.getNome().isEmpty()){
            customerUpdated.setNome(customerInfo.getNome());
        }
        if (!customerInfo.getCognome().isEmpty()){
            customerUpdated.setCognome(customerInfo.getCognome());
        }
        if (!customerInfo.getTelefono().isEmpty()){
            customerUpdated.setTelefono(customerInfo.getTelefono());
        }
        if (!customerInfo.getEmail().isEmpty()){
            customerUpdated.setEmail(customerInfo.getEmail());
        }
        if (!customerInfo.getPassword().isEmpty()){
            customerUpdated.setPassword(customerInfo.getPassword());
        }
        customerRepository.save(customerUpdated);
        return customerMapper.toDTO(customerUpdated);
    }

    @Override
    public List<VehicleDTO> findByMarca(String marca) {
        List<Vehicle> vehicleList = vehicleRepository.findByMarca(marca);
        return vehicleMapper.toDTOList(vehicleList);
    }

    @Override
    public List<VehicleDTO> findByModello(String modello) {
        List<Vehicle> vehicleList = vehicleRepository.findByModello(modello);
        return vehicleMapper.toDTOList(vehicleList);
    }

    @Override
    public List<VehicleDTO> findByColore(String colore) {
        List<Vehicle> vehicleList = vehicleRepository.findByColore(colore);
        return vehicleMapper.toDTOList(vehicleList);
    }

    @Override
    public List<VehicleDTO> findByCilindrata(int cilindrata) {
        List<Vehicle> vehicleList = vehicleRepository.findByCilindrata(cilindrata);
        return vehicleMapper.toDTOList(vehicleList);
    }

    @Override
    public List<VehicleDTO> findByCambio(String cambio) {
        List<Vehicle> vehicleList = vehicleRepository.findByCambio(cambio);
        return vehicleMapper.toDTOList(vehicleList);
    }


    @Override
    public VehicleDTO getVehicleInfo(int vehicleId) {
        Optional<Vehicle> vehicleTemp = vehicleRepository.findById(vehicleId);
        if(vehicleTemp.isEmpty()){
            throw new RuntimeException("no vehicle found at id " + vehicleId);
        }
        return vehicleMapper.toDTO(vehicleTemp.get());
    }
}
