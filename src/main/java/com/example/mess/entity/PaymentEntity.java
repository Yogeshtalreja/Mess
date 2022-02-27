package com.example.mess.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Audited
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

    @Column(name = "created_at")
    private Timestamp createdAt;

    //member_username (relation with member user)

    // mess_id (relation with mess)

    @Version
    private Integer version;

}
