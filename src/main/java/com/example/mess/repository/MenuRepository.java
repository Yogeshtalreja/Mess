package com.example.mess.repository;

import com.example.mess.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity,Integer> {

    List<MenuEntity> findAllByMessId(String messId);

}
