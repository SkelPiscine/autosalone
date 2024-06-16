package com.example.autosalone.mapper;

import com.example.autosalone.entity.users.Seller;
import com.example.autosalone.model.SellerDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-16T16:07:24+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class SellerMapperImpl implements SellerMapper {

    @Override
    public SellerDTO toDTO(Seller seller) {
        if ( seller == null ) {
            return null;
        }

        SellerDTO sellerDTO = new SellerDTO();

        return sellerDTO;
    }

    @Override
    public Seller toEntity(SellerDTO sellerDTO) {
        if ( sellerDTO == null ) {
            return null;
        }

        Seller seller = new Seller();

        return seller;
    }
}
