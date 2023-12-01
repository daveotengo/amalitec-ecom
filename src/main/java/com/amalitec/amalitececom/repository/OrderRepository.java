package com.amalitec.amalitececom.repository;

import com.amalitec.amalitececom.model.Order;
import com.amalitec.amalitececom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID>
{
    Optional<Boolean> deleteOrderById(UUID id);
    Optional<Order> findById(UUID id);

}
