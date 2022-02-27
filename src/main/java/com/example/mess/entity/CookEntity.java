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

        // mess_id (relation with mess)

        @Version
        private Integer version;
}
