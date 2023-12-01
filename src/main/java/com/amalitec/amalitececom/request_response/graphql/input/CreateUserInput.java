package com.amalitec.amalitececom.request_response.graphql.input;

import lombok.Data;

@Data
public class CreateUserInput {
    private String name;
    private String email;
    private String password;
}
