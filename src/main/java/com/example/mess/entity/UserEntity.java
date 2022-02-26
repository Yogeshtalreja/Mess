package com.example.mess.entity;


import com.example.mess.enums.UserTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Audited
@Setter
@Getter
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "contact")
    private String contact;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "user_type")
    private Integer userType;

    @Version
    private Integer version;

    @Transient
    private UserTypeEnum type;

    @PrePersist
    @PreUpdate
    void setUserType(){
        this.userType = type.getType();
    }
}
