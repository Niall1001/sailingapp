package com.unosquare.sailingapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "boat_owner")
@AllArgsConstructor
@NoArgsConstructor
public class BoatOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    @Column(name = "boat_id")
    private int boatId;
    @Column(name = "user_id")
    private int userId;
}
