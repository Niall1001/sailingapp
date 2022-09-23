package com.unosquare.sailingapp.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(final String message){
        super(message);
    }
}
