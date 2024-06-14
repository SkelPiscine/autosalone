package com.example.autosalone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerDTO {
    private String nome;
    private String cognome;
    private String telefono;
    private String email;
    private String password;
}
