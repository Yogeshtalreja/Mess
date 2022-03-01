package com.example.mess.entity;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.sql.Timestamp;


@Setter
@Getter
@Entity
@Table(name = "expenses")
public class ExpenseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "Description")
    private String description;

    @Column(name = "recipt")
    private String recipt;

    @Column(name = "created_at")
    private Timestamp createdAt;

    // mess_id (relation with mess)
    @ManyToOne
    @JoinColumn(name = "messes_id")
    private MessEntity mess;

    @Version
    private Integer version;
}
