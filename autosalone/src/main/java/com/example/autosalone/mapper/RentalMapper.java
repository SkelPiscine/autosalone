package com.example.autosalone.mapper;

import com.example.autosalone.entity.operations.Purchase;
import com.example.autosalone.entity.operations.Rental;
import com.example.autosalone.model.PurchaseDTO;
import com.example.autosalone.model.RentalDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RentalMapper {
    RentalDTO toDTO(Rental rental);
    Rental toEntity(RentalDTO rentalDTO);
    List<RentalDTO> toDTOList(List<Rental> rentalList);
    List<Rental> toEntityList(List<RentalDTO> rentalDTOList);
}
