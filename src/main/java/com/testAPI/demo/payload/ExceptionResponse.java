package com.testAPI.demo.payload;

import lombok.Data;

@Data
public class ExceptionResponse {

    public static final String INVALID_REQUEST ="ERROR: Invalid Request.";

    private String errorMessage;
    private String requestedURI;


    public void callerURL(final String requestedURI) {
        this.requestedURI = requestedURI;
    }

}