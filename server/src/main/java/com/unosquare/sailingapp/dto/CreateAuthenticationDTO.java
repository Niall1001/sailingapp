package com.unosquare.sailingapp.dto;

import lombok.Data;

@Data
public class CreateAuthenticationDTO {
    private String emailAddress;
    private String password;
}
