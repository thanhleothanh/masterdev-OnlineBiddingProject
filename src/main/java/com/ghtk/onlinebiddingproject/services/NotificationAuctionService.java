package com.ghtk.onlinebiddingproject.services;

import com.ghtk.onlinebiddingproject.models.entities.Auction;
import com.ghtk.onlinebiddingproject.models.entities.Notification;
import com.ghtk.onlinebiddingproject.models.entities.NotificationAuction;

public interface NotificationAuctionService {
    NotificationAuction getById(Integer id);

    NotificationAuction save(Auction auction, Notification notification);

    NotificationAuction getNotificationByAuctionId(Integer auctionId);
}
