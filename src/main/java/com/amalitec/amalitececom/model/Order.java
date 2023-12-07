package com.amalitec.amalitececom.model;

import com.amalitec.amalitececom.blueprint.BaseEntity;
import jakarta.persistence.*;

import lombok.*;

import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "\"ORDER\"")
//@Table(name ="order")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Order extends BaseEntity {

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
    private List<ProductLine> productLineList;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;



}
