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
//
//    // json type column named data
//    @Type(type = "json")
//    @Column(columnDefinition = "jsonb", name = "data")
//    private Map<String,String> data;

    @Column(name = "readed")
    private Integer readed;

    @Column(name = "message")
    private String message;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "mess_id")
    private MessEntity mess;

}
