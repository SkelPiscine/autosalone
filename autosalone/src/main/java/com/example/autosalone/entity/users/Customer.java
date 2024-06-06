package com.example.autosalone.entity.users;

import jakarta.persistence.Entity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Customer {
    private int id;
    private String nome;
    private String cognome;
    private String telefono;
    private String email;
    private String password;
}
