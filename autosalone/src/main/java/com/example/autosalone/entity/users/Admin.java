package com.example.autosalone.entity.users;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Admin {
    private String nome;
    private String cognome;
    private String email;
    private String password;
}
