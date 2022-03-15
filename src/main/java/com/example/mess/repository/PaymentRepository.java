package com.example.mess.repository;

import com.example.mess.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentEntity,Integer> {

    @Query("select payments from PaymentEntity payments where payments.date >= ?2 and payments.user.id=?1 ")
    List<PaymentEntity> paymentsOfMonthByUser(Integer userId, Timestamp timestamp);

}
