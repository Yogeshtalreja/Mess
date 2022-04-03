package com.example.mess.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name = "units_offs")
public class UnitOffEntity {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "breakfast_off")
    private Boolean breakfast;

    @Column(name = "lunch_off")
    private Boolean lunch;

    @Column(name = "dinner_off")
    private Boolean dinner;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "username")
    private String memberUsername;

    @ManyToOne
    @JoinColumn(name = "mess_id")
    private MessEntity mess;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserEntity user;

    @Version
    private Integer version;
}
