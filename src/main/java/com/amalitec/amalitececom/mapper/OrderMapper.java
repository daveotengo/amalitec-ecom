package com.amalitec.amalitececom.mapper;




import com.amalitec.amalitececom.blueprint.EntityMapper;
import com.amalitec.amalitececom.model.Product;
import com.amalitec.amalitececom.model.ProductLine;
import com.amalitec.amalitececom.model.User;
import com.amalitec.amalitececom.request_response.graphql.input.OrderInput;
import com.amalitec.amalitececom.model.Order;
import com.amalitec.amalitececom.request_response.graphql.input.ProductLineInput;
import com.amalitec.amalitececom.service.ProductService;
import com.amalitec.amalitececom.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring", uses = {ProductLineMapper.class, UserMapper.class})
public interface OrderMapper extends EntityMapper<OrderInput, Order> {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Named("toDtoFromOptional")
    default OrderInput toDtoFromOptional(Optional<Order> optionalOrder) {
        return optionalOrder.map(this::toDto).orElse(null);
    }

    default Order fromId(UUID id) {
//        Order order = new Order();
//        order.setId(id);
        //return id == null ? null : order;
        if (id == null) {
            return null;
        }
        return OrderMapper.INSTANCE.toEntity(id);
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "userId", qualifiedByName = "toUser")
    @Mapping(target = "productLineList", source = "productLineInputList", qualifiedByName = "toProductLines")
    Order toEntity(OrderInput orderInput);

    @Named("toUser")
    default User toUser(UUID userId) {
        User user = new User();
        user.setId(userId);
        return user;
        //return UserMapper.INSTANCE.toEntity(userId);

    }

    @Named("toProductLines")
    default List<ProductLine> toProductLines(List<ProductLineInput> productLineInputs) {
        return productLineInputs.stream()
                .map(this::toProductLine)
                .collect(Collectors.toList());
    }

    default ProductLine toProductLine(ProductLineInput productLineInput) {
        ProductLine productLine = new ProductLine();

        UUID productId = productLineInput.getProductId();
        Integer quantity = productLineInput.getQuantity();

        //get application context

        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(
                "com.amalitec.amalitececom.service");
        ProductService productService = (ProductService) context.getBean("ProductService");

        // Perform null check on productService
        productService.getProductById(productId)
                .ifPresent(product -> productLine.setProduct(product));


        productLine.setQuantity(quantity);

        return productLine;
    }


    Order toEntity(UUID orderId);




}