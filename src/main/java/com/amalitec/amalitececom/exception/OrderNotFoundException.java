package com.amalitec.amalitececom.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.*;


public class OrderNotFoundException extends RuntimeException implements GraphQLError {

    private UUID order;


    private Map<String, Object> extensions = new HashMap<>();



    public OrderNotFoundException(String message, Long order) {
        super(message);
        extensions.put("order", order);
    }



    public OrderNotFoundException(UUID orderId) {
        this.order = orderId;
    }



    @Override
    public String getMessage() {
        return "Order with ID " + order + " could not be found";
    }



    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }



    @Override
    public Map<String, Object> getExtensions() {
        return Collections.singletonMap("order", order);
    }



    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }
}
