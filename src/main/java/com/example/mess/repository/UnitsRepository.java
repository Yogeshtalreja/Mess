package com.example.mess.repository;

import com.example.mess.entity.UnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface UnitsRepository extends JpaRepository<UnitEntity,Integer> {

    @Query("select units from UnitEntity units where units.date >= ?2 and units.user.id=?1 ")
    List<UnitEntity> findBySameMonth(Integer userId, Timestamp date);

    @Query("select units from UnitEntity units where units.date >= ?2 and units.user.mess.id = ?1 ")
    List<UnitEntity> findUnitsOfMessByMonth(String messId, Timestamp date);

}
