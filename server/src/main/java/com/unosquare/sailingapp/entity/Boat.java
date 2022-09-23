package com.unosquare.sailingapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "boat")
@AllArgsConstructor
@NoArgsConstructor
public class Boat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "sail_no")
    private String sailNo;
    @Column(name = "boat_class")
    private String boatClass;
    @Column(name = "age")
    private int age;
    @Column(name = "description")
    private String description;
}
