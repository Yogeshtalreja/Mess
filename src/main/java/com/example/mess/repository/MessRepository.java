package com.example.mess.repository;

import com.example.mess.entity.MessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessRepository extends JpaRepository<MessEntity,String> {

    @Query("select mess.id from MessEntity mess")
    List<String> findAllIds();
}
