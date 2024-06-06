package com.example.autosalone.mapper;

import com.example.autosalone.entity.operations.Order;
import com.example.autosalone.entity.operations.Purchase;
import com.example.autosalone.model.OrderDTO;
import com.example.autosalone.model.PurchaseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {
    PurchaseDTO toDTO(Purchase purchase);
    Purchase toEntity(PurchaseDTO purchaseDTO);
    List<PurchaseDTO> toDTOList(List<Purchase> purchaseList);
    List<Purchase> toEntityList(List<PurchaseDTO> purchaseDTOList);
}
