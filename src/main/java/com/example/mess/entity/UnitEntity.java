package com.example.mess.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name = "units")
public class UnitEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number_of_units")
    private Integer units;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "username")
    private String memberUsername;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserEntity user;
    // user_id kadhon eendi?

    @ManyToOne
    @JoinColumn(name = "mess_id")
    private MessEntity mess;

    @Version
    private Integer version;
}
