package com.example.mess.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.sql.Timestamp;

@Audited
@Setter
@Getter
@Entity
@Table(name = "messes")
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

    // member_username (user ke sath relation)

    // cook_username ( cooks table ke sath relation)

    @Version
    private Integer version;


}
