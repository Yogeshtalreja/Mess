package com.example.mess.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import java.sql.Timestamp;


@Setter
@Getter
@Entity
@Table(name = "payment_infos")
public class PaymentEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "paidamount")
    private Integer paid_amount;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "member_username")
    private String memberUsername;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserEntity user;

//  how will we get user_id here

    @ManyToOne
    @JoinColumn(name = "mess_id")
    private MessEntity mess;

    @Version
    private Integer version;

}
