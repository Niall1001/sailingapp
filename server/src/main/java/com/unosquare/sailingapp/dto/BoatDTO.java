package com.unosquare.sailingapp.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@RequiredArgsConstructor
public class BoatDTO {
    private int id;
    private String name;
    private String sailNo;
    private String boatClass;
    private int age;
    private String description;
}
