package com.unosquare.sailingapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
public class EventBoatViewModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int eventId;
    private int boatId;
    private int position;
    private Date time;
}
