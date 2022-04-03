package com.example.mess.repository;

import com.example.mess.entity.MessEntity;
import com.example.mess.entity.NotificationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationRepository extends JpaRepository<NotificationsEntity,Integer> {

}
