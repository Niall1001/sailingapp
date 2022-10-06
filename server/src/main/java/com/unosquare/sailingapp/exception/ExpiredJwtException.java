package com.unosquare.sailingapp.exception;

public class ExpiredJwtException extends RuntimeException{
    public ExpiredJwtException(final String message){
        super(message);
    }
}
