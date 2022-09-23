package com.unosquare.sailingapp.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class EventDTO {
    private int id;
    private String name;
    private String type;
    private Date date;
    private String description;
    private int status;
}
