package com.ghtk.onlinebiddingproject.services;

import com.ghtk.onlinebiddingproject.constants.EntityTypeConstants;
import com.ghtk.onlinebiddingproject.models.entities.Auction;
import com.ghtk.onlinebiddingproject.models.entities.Notification;
import com.ghtk.onlinebiddingproject.models.entities.NotificationNotified;
import com.ghtk.onlinebiddingproject.models.entities.NotificationNotifier;

import java.util.List;

public interface NotificationService {
    Notification getById(Integer id);

    Notification adminSaveAuctionNotification(Auction auction, Notification notification);

}
