package com.unosquare.sailingapp.dto;

import lombok.Data;

import java.util.Date;
@Data
public class CreateAppUserDTO {
    private String name;
    private String emailAddress;
    private Date dob;
    private String password;
    private int user_type;
}
