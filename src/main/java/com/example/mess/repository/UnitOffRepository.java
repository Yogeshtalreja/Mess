package com.example.mess.repository;

import com.example.mess.entity.UnitEntity;
import com.example.mess.entity.UnitOffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface UnitOffRepository extends JpaRepository<UnitOffEntity,Integer> {

    @Query("select units from UnitOffEntity units where units.date >= ?2 and units.user.id=?1 ")
    List<UnitOffEntity> findBySameMonth(Integer userId, Timestamp date);

}
