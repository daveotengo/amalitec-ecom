package com.amalitec.amalitececom.request_response.graphql.output;

import com.amalitec.amalitececom.model.Order;
import lombok.Data;

@Data
public class OrderOutput {
    private String message;
    private String status;
    private Order order;
}
