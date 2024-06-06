package com.example.autosalone.entity.vehicles;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public abstract class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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
