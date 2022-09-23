package com.unosquare.sailingapp.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateEventBoatDTO {
    private int id;
    private int eventId;
    private int boatId;
    private int position;
    private Date time;
}
