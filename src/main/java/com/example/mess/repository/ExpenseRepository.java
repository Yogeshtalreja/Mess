package com.example.mess.repository;

import com.example.mess.entity.ExpenseEntity;
import com.example.mess.entity.GuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity,Integer> {

    @Query("select expense from ExpenseEntity expense where expense.date >= ?2 and expense.mess.id = ?1")
    List<ExpenseEntity> findBySameMonth(String messId, Timestamp date);

}
