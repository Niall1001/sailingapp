package com.unosquare.sailingapp.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class AppUserDTO {
    private int id;
    private String name;
    private String emailAddress;
    private Date dob;
    private String password;
    private GrabUserAccessStatusDTO userAccessStatus;
}
