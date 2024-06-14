package com.example.autosalone.mapper;

import com.example.autosalone.entity.users.Customer;
import com.example.autosalone.entity.users.Seller;
import com.example.autosalone.model.CustomerDTO;
import com.example.autosalone.model.SellerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SellerMapper {
    SellerDTO toDTO(Seller seller);
    Seller toEntity(SellerDTO sellerDTO);

}
