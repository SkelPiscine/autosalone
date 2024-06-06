package com.example.autosalone.mapper;

import com.example.autosalone.entity.operations.Order;
import com.example.autosalone.entity.operations.Purchase;
import com.example.autosalone.model.OrderDTO;
import com.example.autosalone.model.PurchaseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO toDTO(Order order);
    Order toEntity(OrderDTO orderDTO);
    List<OrderDTO> toDTOList(List<Order> orderList);
    List<Order> toEntityList(List<OrderDTO> orderDTOList);
}
