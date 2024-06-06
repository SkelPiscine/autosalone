package com.example.autosalone.model;

import com.example.autosalone.entity.vehicles.Vehicle;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private double anticipo;
    private boolean pagato;
    private String statoOrdine;
    private Vehicle veicolo;
}
