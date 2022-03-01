package com.example.mess.entity;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.sql.Timestamp;


@Setter
@Getter
@Entity
@Table(name = "cooks")
public class CookEntity {

    @Id
    @Column(name = "cook_username")
    private String username;

    @Column(name = "full_name")
    private String name;

    @Column(name = "contact_no")
    private Integer about;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    private Timestamp createdAt;

   @ManyToOne
   @JoinColumn(name = "messes_id")
   private MessEntity mess;

    @Version
    private Integer version;
}
