package com.amalitec.amalitececom.controller.graphql;

import com.amalitec.amalitececom.request_response.graphql.input.OrderInput;
import com.amalitec.amalitececom.request_response.graphql.input.ProductLineInput;
import com.amalitec.amalitececom.request_response.graphql.input.UpdateOrderInput;
import com.amalitec.amalitececom.request_response.graphql.output.AllOrderOutput;
import com.amalitec.amalitececom.request_response.graphql.output.DeletionStatus;
import com.amalitec.amalitececom.request_response.graphql.output.OrderOutput;
import com.amalitec.amalitececom.mapper.OrderMapper;
import com.amalitec.amalitececom.model.Order;
import com.amalitec.amalitececom.model.Product;
import com.amalitec.amalitececom.model.ProductLine;
import com.amalitec.amalitececom.model.User;
import com.amalitec.amalitececom.service.OrderService;
import com.amalitec.amalitececom.service.ProductService;
import com.amalitec.amalitececom.service.UserService;
import com.amalitec.amalitececom.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@RequiredArgsConstructor
@Controller
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;
    private final UserService userService;

    private final OrderMapper orderMapper;
    private final ProductService productService;

    @Transactional(readOnly = true)
    @QueryMapping
    public AllOrderOutput getAllOrders() {
        logger.info("Getting all orders...");
        List<Order> orders = orderService.getAllOrders();
        logger.info("Retrieved count of orders: {}", orders.size());
        AllOrderOutput allOrderOutput = new AllOrderOutput();
        allOrderOutput.setOrderList(orders);
        allOrderOutput.setMessage(Constants.SUCCESS);
        allOrderOutput.setStatus(Constants.SUCCESS_STATUS);
        return allOrderOutput;
    }

    @Transactional(readOnly = true)
    @QueryMapping
    public OrderOutput getOrderById(@Argument UUID id) {
        Optional<Order> optionalOrder = orderService.getOrderById(id);
        OrderOutput orderOutput = new OrderOutput();
        try {

            if(!optionalOrder.isPresent()){
                orderOutput.setMessage(Constants.NOTFOUND);
                orderOutput.setStatus(Constants.FAILURE_STATUS_01);
                return orderOutput;
            }else{
                Order order = optionalOrder.get();
                orderOutput.setOrder(order);
                orderOutput.setMessage(Constants.SUCCESS);
                orderOutput.setStatus(Constants.SUCCESS_STATUS);
            }

        }catch (Exception e){
            e.printStackTrace();
            orderOutput.setMessage(Constants.FAILURE_MESSAGE);
            orderOutput.setStatus(Constants.FAILURE_STATUS_02);
        }
        return orderOutput;
    }


    @Transactional
    @MutationMapping
    public OrderOutput createOrder(@Argument OrderInput input) {
            OrderOutput orderOutput = new OrderOutput();

            try {
                List<ProductLine> productLineList = new ArrayList<>();
                logger.info("input: {}", input);
                Order order = new Order();
                UUID userId =input.getUserId();

                Optional<User> optionalUser = userService.getUserById(userId);
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    order.setUser(user);
                }

                logger.info("order: {}", order);

                for(ProductLineInput productLineInput:  input.getProductLineInputList()) {
                    ProductLine productLine= new ProductLine();

                    UUID productId = productLineInput.getProductId();
                    Integer quantity = productLineInput.getQuantity();
                    Optional<Product> optionalProduct = productService.getProductById(productId);
                    if(optionalProduct.isPresent()){
                        Product product = optionalProduct.get();
                        productLine = new ProductLine(quantity, product, order);
                    }
                    productLineList.add(productLine);
                }
                order.setProductLineList(productLineList);
                Order createdOrder = orderService.createOrder(order);
                logger.info("Order created successfully: {}", createdOrder);
                orderOutput.setOrder(createdOrder);
                orderOutput.setMessage(Constants.SUCCESS);
                orderOutput.setStatus(Constants.SUCCESS_STATUS);
            }catch (Exception e){
                e.printStackTrace();
                orderOutput.setMessage(Constants.FAILURE_MESSAGE);
                orderOutput.setStatus(Constants.FAILURE_STATUS_01);
            }

            return orderOutput;
    }



    @Transactional
    @MutationMapping
    public OrderOutput updateOrder(@Argument UpdateOrderInput input) {
        OrderOutput orderOutput = new OrderOutput();
        UUID id = input.getId();
        //Order order=orderMapper.toEntity(input);
        try {
            Order order = new Order();
            UUID userId =input.getUserId();
            List<ProductLine> productLineList = new ArrayList<>();
            logger.info("input: {}", input);

            Optional<User> optionalUser =userService.getUserById(userId);
            if(optionalUser.isPresent()) {
                User user = optionalUser.get();
                order = Order.builder()
                        .user(user) // Assign the user who placed the order
                        .build();
            }

            logger.info("order: {}", order);

            for(ProductLineInput productLineInput:  input.getProductLineInputList()) {
                ProductLine productLine= new ProductLine();

                UUID productId = productLineInput.getProductId();
                Integer quantity = productLineInput.getQuantity();
                Optional<Product> optionalProduct = productService.getProductById(productId);
                if(optionalProduct.isPresent()){
                    Product product = optionalProduct.get();
                    productLine = new ProductLine(quantity, product, order);
                }
                productLineList.add(productLine);
            }
            order.setProductLineList(productLineList);

            Order updatedOrder = orderService.updateOrder(id, order);
            if(Objects.nonNull(updatedOrder)){
                orderOutput.setMessage(Constants.SUCCESS);
                orderOutput.setStatus(Constants.SUCCESS_STATUS);
                orderOutput.setOrder(updatedOrder);
            }else {
                orderOutput.setMessage(Constants.FAILURE_MESSAGE);
                orderOutput.setStatus(Constants.FAILURE_STATUS_01);
            }
        }catch (Exception e){
            e.printStackTrace();
            orderOutput.setMessage(Constants.FAILURE_MESSAGE);
            orderOutput.setStatus(Constants.FAILURE_STATUS_02);
        }
        return orderOutput;
    }

    @Transactional
    @MutationMapping
    public DeletionStatus deleteOrder(@Argument UUID id) {
        logger.info("Order with id: {} being deleted", id);
        try {
            if (!orderService.existsById(id)) {
                logger.info("order with id: {} not found",id);
                return new DeletionStatus(false, "Order not found");
            }
            orderService.deleteOrder(id);
            logger.info("order with id: {} deleted successfully",id);
        }catch (Exception e){
            e.printStackTrace();
            return new DeletionStatus(false, "Order Deletion failed");
        }
        return new DeletionStatus(true, "Order deleted successfully");
    }

}
