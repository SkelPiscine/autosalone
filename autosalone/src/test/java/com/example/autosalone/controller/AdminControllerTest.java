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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {
    @Mock
    AdminService adminService;

    @InjectMocks
    AdminController adminController;

    @Test
    public void testAddVehicleSuccess() throws Exception {

        VehicleDTO vehicleDto = new VehicleDTO().builder()
                .marca("TestMarca")
                .modello("TestModello")
                .colore("TestColore")
                .build();

        when(adminService.addVehicle(vehicleDto)).thenReturn(vehicleDto);

        ResponseEntity<VehicleDTO> response = adminController.addVehicle(vehicleDto);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vehicleDto, response.getBody());
    }
    @Test
    public void testAddVehicleError() throws Exception {

        VehicleDTO vehicleDto = new VehicleDTO().builder()
                .marca("TestMarca")
                .modello("TestModello")
                .colore("TestColore")
                .build();

        when(adminService.addVehicle(vehicleDto)).thenThrow(new RuntimeException("Errore nel servizio"));

        ResponseEntity<VehicleDTO> response = adminController.addVehicle(vehicleDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }
}
