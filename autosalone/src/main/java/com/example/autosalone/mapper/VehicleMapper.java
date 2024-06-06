package com.example.autosalone.mapper;

import com.example.autosalone.entity.users.Customer;
import com.example.autosalone.entity.vehicles.Vehicle;
import com.example.autosalone.model.CustomerDTO;
import com.example.autosalone.model.VehicleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    VehicleDTO toDTO(Vehicle vehicle);
    Vehicle toEntity(VehicleDTO vehicleDTO);
}
