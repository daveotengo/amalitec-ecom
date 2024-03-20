package com.amalitec.amalitececom.request_response.graphql.output;

import com.amalitec.amalitececom.model.Order;
import lombok.Data;

import java.util.List;

@Data
public class AllOrderOutput {
    private String message;
    private String status;
    private List<Order> orderList;
}
