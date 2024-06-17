package com.example.autosalone.mapper;

import com.example.autosalone.entity.operations.Rental;
import com.example.autosalone.model.RentalDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-17T19:57:20+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class RentalMapperImpl implements RentalMapper {

    @Override
    public RentalDTO toDTO(Rental rental) {
        if ( rental == null ) {
            return null;
        }

        RentalDTO rentalDTO = new RentalDTO();

        return rentalDTO;
    }

    @Override
    public Rental toEntity(RentalDTO rentalDTO) {
        if ( rentalDTO == null ) {
            return null;
        }

        Rental rental = new Rental();

        return rental;
    }

    @Override
    public List<RentalDTO> toDTOList(List<Rental> rentalList) {
        if ( rentalList == null ) {
            return null;
        }

        List<RentalDTO> list = new ArrayList<RentalDTO>( rentalList.size() );
        for ( Rental rental : rentalList ) {
            list.add( toDTO( rental ) );
        }

        return list;
    }

    @Override
    public List<Rental> toEntityList(List<RentalDTO> rentalDTOList) {
        if ( rentalDTOList == null ) {
            return null;
        }

        List<Rental> list = new ArrayList<Rental>( rentalDTOList.size() );
        for ( RentalDTO rentalDTO : rentalDTOList ) {
            list.add( toEntity( rentalDTO ) );
        }

        return list;
    }
}
