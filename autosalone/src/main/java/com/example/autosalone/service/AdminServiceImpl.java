package com.example.autosalone.service;

import com.example.autosalone.entity.operations.Order;
import com.example.autosalone.entity.operations.Purchase;
import com.example.autosalone.entity.operations.Rental;
import com.example.autosalone.entity.users.Customer;
import com.example.autosalone.entity.users.Seller;
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
public class AdminServiceImpl implements AdminService{
    private final VehicleRepository vehicleRepository;
    private final OrderRepository orderRepository;
    private final RentalRepository rentalRepository;
    private final CustomerRepository customerRepository;
    private final PurchaseRepository purchaseRepository;
    private final SellerRepository sellerRepository;
    private final VehicleMapper vehicleMapper;
    private final OrderMapper orderMapper;
    private final RentalMapper rentalMapper;
    private final CustomerMapper customerMapper;
    private final PurchaseMapper purchaseMapper;
    private final SellerMapper sellerMapper;

    @Override
    public VehicleDTO addVehicle(VehicleDTO vehicleDto) throws Exception {
        Optional<Vehicle> vehicleOpt = vehicleRepository.findByMarcaAndModelloAndColore(vehicleDto.getMarca(),vehicleDto.getModello(),vehicleDto.getColore());
        if(vehicleOpt.isPresent()){
            throw new Exception("Vehicle already exists");
        }
        Vehicle newVehicle = vehicleMapper.toEntity(vehicleDto);
        vehicleRepository.save(newVehicle);
        return vehicleDto;
    }

    @Override
    public VehicleDTO updateVehicle(int vehicleId, VehicleDTO newVehicleDto) throws Exception {
        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(vehicleId);
        if (vehicleOpt.isEmpty()) {
            throw new Exception("Vehicle not found with id: " + vehicleId);
        }
        Vehicle vehicle = vehicleOpt.get();
        if (newVehicleDto.getMarca() != null && !newVehicleDto.getMarca().equals(vehicle.getMarca())) {
            vehicle.setMarca(newVehicleDto.getMarca());
        }
        if (newVehicleDto.getModello() != null && !newVehicleDto.getModello().equals(vehicle.getModello())) {
            vehicle.setModello(newVehicleDto.getModello());
        }
        if (newVehicleDto.getCilindrata() != 0 && newVehicleDto.getCilindrata() != vehicle.getCilindrata()) {
            vehicle.setCilindrata(newVehicleDto.getCilindrata());
        }
        if (newVehicleDto.getColore() != null && !newVehicleDto.getColore().equals(vehicle.getColore())) {
            vehicle.setColore(newVehicleDto.getColore());
        }
        if (newVehicleDto.getPotenza() != 0 && newVehicleDto.getPotenza() != vehicle.getPotenza()) {
            vehicle.setPotenza(newVehicleDto.getPotenza());
        }
        if (newVehicleDto.getCambio() != null && !newVehicleDto.getCambio().equals(vehicle.getCambio())) {
            vehicle.setCambio(newVehicleDto.getCambio());
        }
        if (newVehicleDto.getAnnoImmatricolazione() != 0 && newVehicleDto.getAnnoImmatricolazione() != vehicle.getAnnoImmatricolazione()) {
            vehicle.setAnnoImmatricolazione(newVehicleDto.getAnnoImmatricolazione());
        }
        if (newVehicleDto.getAlimentazione() != null && !newVehicleDto.getAlimentazione().equals(vehicle.getAlimentazione())) {
            vehicle.setAlimentazione(newVehicleDto.getAlimentazione());
        }
        if (newVehicleDto.getPrezzo() != 0 && newVehicleDto.getPrezzo() != vehicle.getPrezzo()) {
            vehicle.setPrezzo(newVehicleDto.getPrezzo());
        }
        if (newVehicleDto.getSconto() != 0 && newVehicleDto.getSconto() != vehicle.getSconto()) {
            vehicle.setSconto(newVehicleDto.getSconto());
        }
        if (newVehicleDto.getAccessori() != null && !newVehicleDto.getAccessori().equals(vehicle.getAccessori())) {
            vehicle.setAccessori(newVehicleDto.getAccessori());
        }
        if (newVehicleDto.isNuovo() != vehicle.isNuovo()) {
            vehicle.setNuovo(newVehicleDto.isNuovo());
        }
        if (newVehicleDto.isDisponibile() != vehicle.isDisponibile()) {
            vehicle.setDisponibile(newVehicleDto.isDisponibile());
        }
        if (newVehicleDto.isOrdinabile() != vehicle.isOrdinabile()) {
            vehicle.setOrdinabile(newVehicleDto.isOrdinabile());
        }

        vehicleRepository.save(vehicle);
        return vehicleMapper.toDTO(vehicle);
    }

    @Override
    public void deleteVehicle(int vehicleId) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleId);
        if (optionalVehicle.isEmpty()) {
            throw new RuntimeException("Vehicle not found at id " + vehicleId);
        }
        vehicleRepository.deleteById(vehicleId);
    }

    @Override
    public OrderDTO createOrderForUser(OrderDTO orderDto, int customerId) throws Exception {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isEmpty()) {
            throw new Exception("Customer not found at id " + customerId);
        }
        Customer customer = customerOpt.get();
        Order order = new Order().builder()
                .anticipo(orderDto.getAnticipo())
                .pagato(orderDto.isPagato())
                .statoOrdine(orderDto.getStatoOrdine())
                .vehicle(orderDto.getVeicolo())
                .customer(customer)
                .build();

        Order newOrder = orderRepository.save(order);
        return orderMapper.toDTO(newOrder);
    }

    @Override
    public void deleteOrderForUser(int orderId) throws Exception {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()) {
            throw new Exception("Order not found with id: " + orderId);
        }
        orderRepository.deleteById(orderId);
    }

    @Override
    public OrderDTO modifyOrderForUser(int orderId, OrderDTO orderDTO) throws Exception {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isEmpty()) {
            throw new Exception("Order not found with id: " + orderId);
        }
        Order order = orderOpt.get();
        if (orderDTO.getAnticipo() != 0) {
            order.setAnticipo(orderDTO.getAnticipo());
        }
        if (orderDTO.isPagato() != order.isPagato()) {
            order.setPagato(orderDTO.isPagato());
        }
        if (orderDTO.getStatoOrdine() != null) {
            order.setStatoOrdine(orderDTO.getStatoOrdine());
        }
        if (orderDTO.getVeicolo() != null) {
            Optional<Vehicle> optionalVehicle = vehicleRepository.findById(orderDTO.getVeicolo().getId());
            if (optionalVehicle.isEmpty()) {
                throw new Exception("Vehicle not found with id: " + orderDTO.getVeicolo().getId());
            }
            order.setVehicle(optionalVehicle.get());
        }

        Order updatedOrder = orderRepository.save(order);
        return orderMapper.toDTO(updatedOrder);
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

    @Override
    public PurchaseDTO createPurchaseForUser(PurchaseDTO purchaseDto, int customerId) throws Exception {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isEmpty()) {
            throw new Exception("Customer not found with id: " + customerId);
        }
        Customer customer = customerOpt.get();

        Purchase purchase = new Purchase().builder()
                                .anticipo(purchaseDto.getAnticipo())
                                    .pagato(purchaseDto.isPagato())
                                        .statoOrdine(purchaseDto.getStatoOrdine())
                                                .vehicle(purchaseDto.getVeicolo())
                                                        .customer(customer)
                                                                .build();

        Purchase newPurchase = purchaseRepository.save(purchase);
        return purchaseMapper.toDTO(newPurchase);
    }

    @Override
    public void deletePurchaseForUser(int purchaseId) throws Exception {
        Optional<Purchase> optionalPurchase = purchaseRepository.findById(purchaseId);
        if (optionalPurchase.isEmpty()) {
            throw new Exception("Purchase not found with id: " + purchaseId);
        }
        purchaseRepository.deleteById(purchaseId);
    }

    @Override
    public PurchaseDTO modifyPurchaseForUser(int purchaseId, PurchaseDTO updatedPurchaseDto) throws Exception {
        Optional<Purchase> purchaseOpt = purchaseRepository.findById(purchaseId);
        if (purchaseOpt.isEmpty()) {
            throw new Exception("Purchase not found with id: " + purchaseId);
        }
        Purchase purchase = purchaseOpt.get();

        if (updatedPurchaseDto.getAnticipo() != 0) {
            purchase.setAnticipo(updatedPurchaseDto.getAnticipo());
        }
        if (updatedPurchaseDto.isPagato() != purchase.isPagato()) {
            purchase.setPagato(updatedPurchaseDto.isPagato());
        }
        if (updatedPurchaseDto.getVeicolo() != null) {
            Optional<Vehicle> optionalVehicle = vehicleRepository.findById(updatedPurchaseDto.getVeicolo().getId());
            if (optionalVehicle.isEmpty()) {
                throw new Exception("Vehicle not found with id: " + updatedPurchaseDto.getVeicolo().getId());
            }
            purchase.setVehicle(optionalVehicle.get());
        }

        Purchase updatedPurchase = purchaseRepository.save(purchase);
        return purchaseMapper.toDTO(updatedPurchase);
    }

    @Override
    public List<VehicleDTO> getOrdinabileVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findByOrdinabile(true);
        return vehicleMapper.toDTOList(vehicles);
    }

    @Override
    public List<VehicleDTO> getAcquistabileVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findByDisponibile(true);
        return vehicleMapper.toDTOList(vehicles);
    }
    @Override
    public List<VehicleDTO> getNuovoVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findByNuovo(true);
        return vehicleMapper.toDTOList(vehicles);
    }

    @Override
    public List<VehicleDTO> getUsatoVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findByNuovo(false);
        return vehicleMapper.toDTOList(vehicles);
    }

    @Override
    public void deleteCustomer(int customerId) throws Exception {
        Optional<Customer> optionalUser = customerRepository.findById(customerId);
        if (optionalUser.isEmpty()) {
            throw new Exception("User not found with id: " + customerId);
        }
        customerRepository.deleteById(customerId);
    }

    @Override
    public CustomerDTO modifyCustomer(int customerId, CustomerDTO customerDTO) throws Exception {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isEmpty()) {
            throw new Exception("Customer not found with id: " + customerId);
        }

        Customer customer = customerOpt.get();

        if (customerDTO.getNome() != null) {
            customer.setNome(customerDTO.getNome());
        }
        if (customerDTO.getCognome() != null) {
            customer.setCognome(customerDTO.getCognome());
        }
        if (customerDTO.getEmail() != null) {
            customer.setEmail(customerDTO.getEmail());
        }
        if (customerDTO.getTelefono() != null) {
            customer.setTelefono(customerDTO.getTelefono());
        }

        Customer updatedCustomer = customerRepository.save(customer);
        return customerMapper.toDTO(updatedCustomer);
    }


    @Override
    public void deleteSeller(int sellerId) throws Exception {
        Optional<Seller> optionalSeller = sellerRepository.findById(sellerId);
        if (optionalSeller.isEmpty()) {
            throw new Exception("Seller not found with id: " + sellerId);
        }
        sellerRepository.deleteById(sellerId);
    }
    @Override
    public SellerDTO modifySeller(int sellerId, SellerDTO updatedSellerDto) throws Exception {
        Optional<Seller> sellerOpt = sellerRepository.findById(sellerId);
        if (sellerOpt.isEmpty()) {
            throw new Exception("Seller not found with id: " + sellerId);
        }
        Seller seller = sellerOpt.get();
        if (updatedSellerDto.getNome() != null) {
            seller.setNome(updatedSellerDto.getNome());
        }
        if (updatedSellerDto.getCognome() != null) {
            seller.setCognome(updatedSellerDto.getCognome());
        }
        if (updatedSellerDto.getEmail() != null) {
            seller.setEmail(updatedSellerDto.getEmail());
        }
        if (updatedSellerDto.getTelefono() != null) {
            seller.setTelefono(updatedSellerDto.getTelefono());
        }
        sellerRepository.save(seller);
        return sellerMapper.toDTO(seller);
    }




}
