package com.example.autosalone.mapper;

import com.example.autosalone.entity.operations.Order;
import com.example.autosalone.model.OrderDTO;
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
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDTO toDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        return orderDTO;
    }

    @Override
    public Order toEntity(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Order order = new Order();

        return order;
    }

    @Override
    public List<OrderDTO> toDTOList(List<Order> orderList) {
        if ( orderList == null ) {
            return null;
        }

        List<OrderDTO> list = new ArrayList<OrderDTO>( orderList.size() );
        for ( Order order : orderList ) {
            list.add( toDTO( order ) );
        }

        return list;
    }

    @Override
    public List<Order> toEntityList(List<OrderDTO> orderDTOList) {
        if ( orderDTOList == null ) {
            return null;
        }

        List<Order> list = new ArrayList<Order>( orderDTOList.size() );
        for ( OrderDTO orderDTO : orderDTOList ) {
            list.add( toEntity( orderDTO ) );
        }

        return list;
    }
}
