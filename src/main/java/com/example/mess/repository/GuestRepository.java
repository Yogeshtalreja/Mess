package com.example.mess.repository;

import com.example.mess.entity.GuestEntity;
import com.example.mess.entity.UnitOffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface GuestRepository extends JpaRepository<GuestEntity,Integer> {

    @Query("select units from GuestEntity units where units.date >= ?2 and units.user.id=?1 ")
    List<GuestEntity> findBySameMonth(Integer userId, Timestamp date);

}
