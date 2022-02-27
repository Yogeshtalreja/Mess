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
@Table(name = "guests")
public class GuestEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "num_off_guests")
    private Integer GuestsCount;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "guest_in_breakfast")
    private Boolean inBreakfast;

    @Column(name = "guest_in_lunch")
    private Boolean inLunch;

    @Column(name = "guest_in_dinner")
    private Boolean inDinner;

    @Column(name = "created_at")
    private Timestamp createdAt;

    // mess_id  (relation with mess)

    // member_username (relation with member user)

    @Version
    private Integer version;
}
