package com.example.mess.entity;



import lombok.Getter;
import lombok.Setter;
import netscape.javascript.JSObject;

import javax.persistence.*;
import java.sql.Timestamp;


@Setter
@Getter
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", unique = true)
    private String username;


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "about")
    private String about;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "email_verified_at")
    private Timestamp email_verified_date;

    @Column(name = "password")
    private String password;

    @Column(name = "is_admin")
    private Boolean admin;

    @Column(name = "access")
    private Boolean access;

    @Column(name = "remember_token")
    private String rememberToken;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Version
    private Integer version;

    @ManyToOne
    @JoinColumn(name = "messes_id")
    private MessEntity mess;

    @PreUpdate
    @PrePersist
    void addMemberData(){
        this.access = false;
        this.admin = false;
    }

}
