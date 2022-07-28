package com.ghtk.onlinebiddingproject.repositories;

import com.ghtk.onlinebiddingproject.constants.EntityTypeConstants;
import com.ghtk.onlinebiddingproject.models.entities.Notification;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Integer> {

}
