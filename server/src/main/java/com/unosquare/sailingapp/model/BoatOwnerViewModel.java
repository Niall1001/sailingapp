package com.unosquare.sailingapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
public class BoatOwnerViewModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int boatId;
    private int userId;
}
