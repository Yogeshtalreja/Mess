package com.example.mess.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

@Setter
@Getter
@Entity
@Table(name = "notifications")
public class NotificationsEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data")
    private String data;

    @Column(name = "readed")
    private Integer readed;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "mess_id")
    private MessEntity mess;

}
