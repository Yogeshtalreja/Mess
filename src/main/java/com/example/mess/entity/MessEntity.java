package com.example.mess.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "mess")
public class MessEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "description")
    private String description;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "member_username")
    private String memberUsername; // manager username

    @OneToOne
    private UserEntity manager;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "cook_username")
    private CookEntity cook;

    @Version
    private Integer version;

}
