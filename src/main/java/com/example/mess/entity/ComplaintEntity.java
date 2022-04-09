package com.example.mess.entity;


import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
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

    @Column(name = "feedback_type")
    private String feedBackType;

    @Column(name = "complaint_subject")
    private String complaintSubject;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "username")
    private String memberUsername;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "mess_id")
    private MessEntity mess;

    @Version
    private Integer version;

}
