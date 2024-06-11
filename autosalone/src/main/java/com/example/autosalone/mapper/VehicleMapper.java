package com.example.autosalone.mapper;

import com.example.autosalone.entity.operations.Rental;
import com.example.autosalone.entity.users.Customer;
import com.example.autosalone.entity.vehicles.Vehicle;
import com.example.autosalone.model.CustomerDTO;
import com.example.autosalone.model.RentalDTO;
import com.example.autosalone.model.VehicleDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    VehicleDTO toDTO(Vehicle vehicle);
    Vehicle toEntity(VehicleDTO vehicleDTO);
    List<VehicleDTO> toDTOList(List<Vehicle> vehicleList);
    List<Vehicle> toEntityList(List<VehicleDTO> vehicleDTOList);
}
