package com.example.autosalone.model;

import com.example.autosalone.entity.vehicles.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalDTO {
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private double costoGiornaliero;
    private double costoTotale;
    private boolean pagato;
    private Vehicle veicolo;
}
