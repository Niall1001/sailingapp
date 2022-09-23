package com.unosquare.sailingapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Getter
@Setter
public class UpdateBoatViewModel {
    @NotEmpty
    @Size(min = 1, max = 255)
    private String name;

    @NotEmpty
    @Size(min = 1, max = 255)
    private String sailNo;

    @NotEmpty
    @Size(min = 1, max = 255)
    private String boatClass;

    private Integer age;

    @NotEmpty
    @Size(min = 1, max = 255)
    private String description;
}
