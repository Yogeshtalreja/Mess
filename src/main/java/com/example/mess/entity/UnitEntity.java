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
@Table(name = "units")
public class UnitEntity {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number_of_units")
    private Integer units;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "created_at")
    private Timestamp createdAt;

    //member_username (relation with member user)

    // mess_id (relation with mess)

    @Version
    private Integer version;
}
