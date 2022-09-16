package com.testAPI.demo.payload;

public class ExceptionResponse {

    public static final String INVALID_REQUEST ="ERROR: Invalid Request.";

    private String errorMessage;
    private String requestedURI;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getRequestedURI() {
        return requestedURI;
    }

    public void callerURL(final String requestedURI) {
        this.requestedURI = requestedURI;
    }

}