package com.unosquare.sailingapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Time;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "event_boat")
@AllArgsConstructor
@NoArgsConstructor
public class EventBoat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    @Column(name = "event_id")
    private int eventId;
    @Column(name = "boat_id")
    private int boatId;
    @Column(name = "position")
    private int position;
    @Column(name = "time")
    private Date time;

}
