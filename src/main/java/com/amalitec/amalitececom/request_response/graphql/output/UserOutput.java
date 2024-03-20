package com.amalitec.amalitececom.request_response.graphql.output;

import com.amalitec.amalitececom.model.User;
import lombok.Data;

@Data
public class UserOutput {
    private String message;
    private String status;
    private User user;
}
