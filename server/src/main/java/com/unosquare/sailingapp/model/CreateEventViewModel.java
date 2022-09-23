package com.unosquare.sailingapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Getter
@Setter
public class CreateEventViewModel {
    @NotEmpty
    @Size(min = 1, max = 255)
    private String name;

    @NotEmpty
    @Size(min = 1, max = 100)
    private String type;

    @NotEmpty
    @Size(min = 1, max = 255)
    private String date;

    @NotEmpty
    @Size(min = 1, max = 255)
    private String description;


    private int status;
}
