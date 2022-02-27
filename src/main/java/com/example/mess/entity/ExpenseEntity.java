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
@Table(name = "expenses")
public class ExpenseEntity {

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(name = "amount")
        private Integer amount;

        @Column(name = "Description")
        private String description;

        @Column(name = "recipt")
        private String recipt;

        @Column(name = "created_at")
        private Timestamp createdAt;

        // mess_id (relation with mess)

        @Version
        private Integer version;
}
