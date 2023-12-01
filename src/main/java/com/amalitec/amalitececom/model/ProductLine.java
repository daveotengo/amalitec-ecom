package com.amalitec.amalitececom.model;

import com.amalitec.amalitececom.blueprint.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Data
@Entity
@Table(name = "product_line")
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ProductLine {

    private int quantity;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
