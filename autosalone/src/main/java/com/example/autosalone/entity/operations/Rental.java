package com.example.autosalone.entity.operations;

import com.example.autosalone.entity.vehicles.Vehicle;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "rental")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rental {
    private int id;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private double costoGiornaliero;
    private double costoTotale;
    private boolean pagato;
    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle veicolo;
}
