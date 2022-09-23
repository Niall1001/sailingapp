package com.unosquare.sailingapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Getter
@Setter
public class UpdateEventBoatViewModel {
    @NotEmpty
    @Size(min = 1, max = 255)
    private String eventId;

    @NotEmpty
    @Size(min = 1, max = 100)
    private String boatId;


    private int position;

    private Date time;

}
