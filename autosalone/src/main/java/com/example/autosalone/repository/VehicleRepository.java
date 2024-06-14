package com.example.autosalone.repository;

import com.example.autosalone.entity.vehicles.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    Optional<Vehicle> findByMarcaAndModelloAndColore(String marca, String modello, String colore);
    List<Vehicle> findByMarca(String marca);
    List<Vehicle> findByModello(String modello);
    List<Vehicle> findByColore(String colore);
    List<Vehicle> findByCilindrata(int cilindrata);
    List<Vehicle> findByCambio(String cambio);
    List<Vehicle> findByOrdinabile(boolean ordinabile);
    List<Vehicle> findByDisponibile(boolean disponibile);
    List<Vehicle> findByNuovo(boolean nuovo);


}
