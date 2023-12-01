package com.amalitec.amalitececom.request_response.graphql.output;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public  class DeletionStatus {
    private boolean success;
    private String message;
}
