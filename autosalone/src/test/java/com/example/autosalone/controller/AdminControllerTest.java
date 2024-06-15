package com.example.autosalone.controller;

import com.example.autosalone.model.VehicleDTO;
import com.example.autosalone.service.AdminService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {
    @Mock
    AdminService adminService;

    @InjectMocks
    AdminController adminController;

    @Test
    public void testAddVehicle() throws Exception {
        // Mock data
        VehicleDTO vehicleDto = new VehicleDTO().builder()
                .marca("TestMarca")
                .modello("TestModello")
                .colore("TestColore")
                .build();

        // Mock service behavior
        when(adminService.addVehicle(vehicleDto)).thenReturn(vehicleDto);

        // Chiamata al metodo del controller
        ResponseEntity<VehicleDTO> response = adminController.addVehicle(vehicleDto);

        // Verifica del risultato
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vehicleDto, response.getBody());
    }
}
