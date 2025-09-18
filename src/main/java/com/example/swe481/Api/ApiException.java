package com.example.swe481.Api;

public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}
