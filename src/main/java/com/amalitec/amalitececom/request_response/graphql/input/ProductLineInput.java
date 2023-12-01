package com.amalitec.amalitececom.request_response.graphql.input;

import com.amalitec.amalitececom.blueprint.BaseInput;
import lombok.Data;

import java.util.UUID;

@Data
public class ProductLineInput extends BaseInput {
    private int quantity;
    private UUID orderId;
    private UUID productId;
}