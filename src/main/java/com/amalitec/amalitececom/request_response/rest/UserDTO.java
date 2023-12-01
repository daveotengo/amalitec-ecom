package com.amalitec.amalitececom.request_response.rest;

import lombok.Data;

@Data
public class UserDTO {
    private String name;
    private String email;
    private String password;
}
