package com.unosquare.sailingapp.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
public class CreateBoatDTO {

    private String name;
    private String sailNo;
    private String boatClass;
    private int age;
    private String description;
}
