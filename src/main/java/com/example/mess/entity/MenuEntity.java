package com.example.mess.entity;

import com.example.mess.enums.Days;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "menu")
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class MenuEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "day")
    private Days day;

    private String breakfast;

    private String lunch;

    private String dinner;

    @OneToOne
    @JoinColumn(name = "mess_id")
    private MessEntity mess;

}
