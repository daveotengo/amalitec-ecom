package com.amalitec.amalitececom.mapper;
import com.amalitec.amalitececom.blueprint.EntityMapper;
import com.amalitec.amalitececom.request_response.graphql.input.OrderInput;
import com.amalitec.amalitececom.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Optional;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {})
public interface ProductMapper extends EntityMapper<OrderInput, Order> {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Named("toDtoFromOptional")
    default OrderInput toDtoFromOptional(Optional<Order> optionalOrder) {
        return optionalOrder.map(this::toDto).orElse(null);
    }

    default Order fromId(UUID id) {
        if (id == null) {
            return null;
        }
        Order Order = new Order();
        Order.setId(id);
        return Order;
    }
}
