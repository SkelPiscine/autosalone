package com.example.autosalone.mapper;

import com.example.autosalone.entity.users.Customer;
import com.example.autosalone.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO toDTO(Customer customer);
    Customer toEntity(CustomerDTO customerDTO);

}
