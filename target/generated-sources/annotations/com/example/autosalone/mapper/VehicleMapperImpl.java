package com.example.autosalone.mapper;

import com.example.autosalone.entity.vehicles.Vehicle;
import com.example.autosalone.model.VehicleDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-16T20:17:23+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class VehicleMapperImpl implements VehicleMapper {

    @Override
    public VehicleDTO toDTO(Vehicle vehicle) {
        if ( vehicle == null ) {
            return null;
        }

        VehicleDTO vehicleDTO = new VehicleDTO();

        return vehicleDTO;
    }

    @Override
    public Vehicle toEntity(VehicleDTO vehicleDTO) {
        if ( vehicleDTO == null ) {
            return null;
        }

        Vehicle vehicle = new Vehicle();

        return vehicle;
    }

    @Override
    public List<VehicleDTO> toDTOList(List<Vehicle> vehicleList) {
        if ( vehicleList == null ) {
            return null;
        }

        List<VehicleDTO> list = new ArrayList<VehicleDTO>( vehicleList.size() );
        for ( Vehicle vehicle : vehicleList ) {
            list.add( toDTO( vehicle ) );
        }

        return list;
    }

    @Override
    public List<Vehicle> toEntityList(List<VehicleDTO> vehicleDTOList) {
        if ( vehicleDTOList == null ) {
            return null;
        }

        List<Vehicle> list = new ArrayList<Vehicle>( vehicleDTOList.size() );
        for ( VehicleDTO vehicleDTO : vehicleDTOList ) {
            list.add( toEntity( vehicleDTO ) );
        }

        return list;
    }
}
