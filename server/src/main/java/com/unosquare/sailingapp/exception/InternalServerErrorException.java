package com.unosquare.sailingapp.exception;

public class InternalServerErrorException extends RuntimeException{

    public InternalServerErrorException(final String message){
        super(message);
    }
}
