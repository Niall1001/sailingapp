package com.unosquare.sailingapp.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BoatOwnerDTO {
    private int id;
    private String boatId;
    private String userId;
}
