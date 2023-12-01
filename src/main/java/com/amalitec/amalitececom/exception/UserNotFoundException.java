package com.amalitec.amalitececom.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class UserNotFoundException extends RuntimeException implements GraphQLError {

    private UUID userId;



    public UserNotFoundException(UUID userId) {
        this.userId = userId;
    }



    @Override
    public String getMessage() {
        return "User with ID " + userId + " could not be found";
    }



    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }



    @Override
    public Map<String, Object> getExtensions() {
        return Collections.singletonMap("userId", userId);
    }



    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }
}
