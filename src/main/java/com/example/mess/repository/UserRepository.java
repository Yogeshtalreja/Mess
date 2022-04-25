package com.example.mess.repository;

import com.example.mess.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    Optional<UserEntity> findByEmailAndPassword(String email, String password);

    @Query("select count(user) from UserEntity user where user.mess.id = ?1")
    Integer totalUsersOfMess(String messId);

    Optional<UserEntity> findByEmail(String email);

    Integer countDistinctByMessId(String messId);
}
