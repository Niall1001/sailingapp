package com.unosquare.sailingapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_access_status")
public class UserAccessStatus implements Serializable {
    @Serial
    private static final long serialVersionUID = 5588031152399768319L;

    @Id
    @SequenceGenerator(name = "user_access_status_id_seq", sequenceName = "user_access_status_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "user_access_status_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "status", nullable = false)
    private String status;
}
