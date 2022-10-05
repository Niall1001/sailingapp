package com.unosquare.sailingapp.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static com.unosquare.sailingapp.constant.AppConstants.*;

@Data
public class CreateAuthenticationViewModel {

    @NotEmpty
    @Size(min = EMAIL_MIN_SIZE, max = EMAIL_MAX_SIZE)
    private String emailAddress;

    @NotEmpty
    @Size(min = PASSWORD_MIN_SIZE, max = PASSWORD_MAX_SIZE)
    private String password;
}
