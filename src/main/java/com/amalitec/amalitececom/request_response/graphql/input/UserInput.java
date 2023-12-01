package com.amalitec.amalitececom.request_response.graphql.input;

import lombok.Data;

import java.util.UUID;

@Data
public class UserInput {
    private UUID id;
    private String name;
    private String email;
    private String password;
}
