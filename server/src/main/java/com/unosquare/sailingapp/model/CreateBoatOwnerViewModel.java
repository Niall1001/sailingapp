package com.unosquare.sailingapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Data
@Getter
@Setter
public class CreateBoatOwnerViewModel {
    @NotEmpty
    private String boatId;
    @NotEmpty
    private String userId;
}
