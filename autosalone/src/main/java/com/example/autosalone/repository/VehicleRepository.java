package com.example.autosalone.repository;

import com.example.autosalone.entity.vehicles.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    List<Vehicle> findByMarca(String marca);
    List<Vehicle> findByModello(String modello);
    List<Vehicle> findByColore(String colore);
    List<Vehicle> findByCilindrata(int cilindrata);
    List<Vehicle> findByCambio(String cambio);

}
