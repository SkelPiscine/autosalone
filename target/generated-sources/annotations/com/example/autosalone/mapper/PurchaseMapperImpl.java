package com.example.autosalone.mapper;

import com.example.autosalone.entity.operations.Purchase;
import com.example.autosalone.model.PurchaseDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-16T16:07:24+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class PurchaseMapperImpl implements PurchaseMapper {

    @Override
    public PurchaseDTO toDTO(Purchase purchase) {
        if ( purchase == null ) {
            return null;
        }

        PurchaseDTO purchaseDTO = new PurchaseDTO();

        return purchaseDTO;
    }

    @Override
    public Purchase toEntity(PurchaseDTO purchaseDTO) {
        if ( purchaseDTO == null ) {
            return null;
        }

        Purchase purchase = new Purchase();

        return purchase;
    }

    @Override
    public List<PurchaseDTO> toDTOList(List<Purchase> purchaseList) {
        if ( purchaseList == null ) {
            return null;
        }

        List<PurchaseDTO> list = new ArrayList<PurchaseDTO>( purchaseList.size() );
        for ( Purchase purchase : purchaseList ) {
            list.add( toDTO( purchase ) );
        }

        return list;
    }

    @Override
    public List<Purchase> toEntityList(List<PurchaseDTO> purchaseDTOList) {
        if ( purchaseDTOList == null ) {
            return null;
        }

        List<Purchase> list = new ArrayList<Purchase>( purchaseDTOList.size() );
        for ( PurchaseDTO purchaseDTO : purchaseDTOList ) {
            list.add( toEntity( purchaseDTO ) );
        }

        return list;
    }
}
