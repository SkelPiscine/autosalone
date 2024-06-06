package com.example.autosalone.entity.operations;

import com.example.autosalone.entity.users.Customer;
import com.example.autosalone.entity.vehicles.Vehicle;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double anticipo;
    private boolean pagato;
    private String statoOrdine;
    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle veicolo;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer cliente;

}
