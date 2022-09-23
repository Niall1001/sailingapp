package com.unosquare.sailingapp.dto;

import lombok.Data;

@Data
public class UpdateBoatDTO {
    private String name;
    private String sailNo;
    private String boatClass;
    private int age;
    private String description;
}
