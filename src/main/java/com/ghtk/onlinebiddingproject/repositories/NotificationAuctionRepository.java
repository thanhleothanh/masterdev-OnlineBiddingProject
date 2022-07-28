package com.ghtk.onlinebiddingproject.repositories;

import com.ghtk.onlinebiddingproject.models.entities.Notification;
import com.ghtk.onlinebiddingproject.models.entities.NotificationAuction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationAuctionRepository extends JpaRepository<NotificationAuction,Integer> {
    NotificationAuction findNotificationByAuctionId(Integer auctionId);
}
