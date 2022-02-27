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
@Table(name = "complaints")
public class ComplaintEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date")
    private Date date;

    @Column(name = "message")
    private String message;

    @Column(name = "picture_proof")
    private String picture;

    @Column(name = "type")
    private String type;

    @Column(name = "created_at")
    private Timestamp createdAt;

    //member_username (relation with member user)

    // mess_id (relation with mess)

    @Version
    private Integer version;

}
