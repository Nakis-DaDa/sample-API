package com.testAPI.demo.exception;

public class GlobalException extends RuntimeException{
    public GlobalException() {
        super();
    }
    public GlobalException(String message) {
        super(message);
    }
}