package com.unosquare.sailingapp.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CreateEventDTO {
    private String name;
    private String type;
    private Date date;
    private String description;
    private int status;
}
