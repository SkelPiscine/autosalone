package com.example.autosalone.entity.operations;

import com.example.autosalone.entity.users.Customer;
import com.example.autosalone.entity.vehicles.Vehicle;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "rental")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private double costoGiornaliero;
    private double costoTotale;
    private boolean pagato;
    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle veicolo;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
