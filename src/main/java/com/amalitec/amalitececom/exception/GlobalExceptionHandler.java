package com.amalitec.amalitececom.exception;

import graphql.ErrorClassification;
import graphql.GraphQLException;
import graphql.language.SourceLocation;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import graphql.GraphQLError;

import java.util.List;
import graphql.GraphQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GraphQLException.class)
    public ResponseEntity<String> handleGraphQLException(GraphQLException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
