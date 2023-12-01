package com.amalitec.amalitececom.service;


import com.amalitec.amalitececom.model.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    List<Order> getAllOrders();
    Optional<Order> getOrderById(UUID id);
    Order createOrder(Order order);
    Order updateOrder(UUID id, Order order);
    boolean deleteOrder(UUID id);

    boolean existsById(UUID id);

    List<Order> createListOfOrders(List<Order> order1);
}

