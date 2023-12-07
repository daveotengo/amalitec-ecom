package com.amalitec.amalitececom.mapper;

import com.amalitec.amalitececom.model.Order;
import com.amalitec.amalitececom.model.Product;
import com.amalitec.amalitececom.model.ProductLine;
import com.amalitec.amalitececom.request_response.graphql.input.ProductLineInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, OrderMapper.class})
public interface ProductLineMapper {
    ProductLineMapper INSTANCE = Mappers.getMapper(ProductLineMapper.class);

    //@Mapping(target = "id", ignore = true)
    @Mapping(target = "product", source = "productId", qualifiedByName = "toProduct")
    @Mapping(target = "order", source = "orderId", qualifiedByName = "toOrder")
    ProductLine toEntity(ProductLineInput productLineInput);

    // Named method to handle optional Product mapping
    @Named("toProduct")
    default Product toProduct(UUID productId) {
        if (productId == null) {
            return null;
        }

        return ProductMapper.INSTANCE.toEntityNew(productId);
    }

    // Named method to handle optional Order mapping
    @Named("toOrder")
    default Order toOrder(UUID orderId) {
        if (orderId == null) {
            return null;
        }

        return OrderMapper.INSTANCE.toEntity(orderId);
    }
}