package com.example.mess.repository;

import com.example.mess.entity.CookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CookRepository extends JpaRepository<CookEntity, String> {

    Optional<CookEntity> findByUsernameAndPassword(String username, String password);
}
