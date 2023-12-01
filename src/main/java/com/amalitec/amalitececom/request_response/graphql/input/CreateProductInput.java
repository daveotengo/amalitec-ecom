package com.amalitec.amalitececom.request_response.graphql.input;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateProductInput {
    private String name;
    private int stock;
    private float price;
}
