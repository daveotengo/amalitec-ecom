package com.amalitec.amalitececom.model;

import com.amalitec.amalitececom.blueprint.BaseEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Product extends BaseEntity {

    @Column(unique = true)
    private String name;
    private int stock;
    private double price;
}
