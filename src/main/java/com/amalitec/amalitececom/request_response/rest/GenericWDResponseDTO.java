package com.amalitec.amalitececom.request_response.rest;


import lombok.Data;

@Data
public class GenericWDResponseDTO {
    private String message;
    private String status;
    private Object data;
}