package com.example.autosalone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleDTO {
    private String marca;
    private String modello;
    private int cilindrata;
    private String colore;
    private short potenza;
    private String cambio;
    private int annoImmatricolazione;
    private String alimentazione;
    private double prezzo;
    private double sconto;
    private List<String> accessori = new ArrayList<>();
    private boolean nuovo;
    private boolean disponibile;
    private boolean ordinabile;
    private boolean nonDisponibile;
}
