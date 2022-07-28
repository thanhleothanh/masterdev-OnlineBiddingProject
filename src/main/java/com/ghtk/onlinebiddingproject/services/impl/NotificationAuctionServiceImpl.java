package com.ghtk.onlinebiddingproject.services.impl;

import com.ghtk.onlinebiddingproject.constants.EntityTypeConstants;
import com.ghtk.onlinebiddingproject.constants.NotificationTypeConstants;
import com.ghtk.onlinebiddingproject.exceptions.NotFoundException;
import com.ghtk.onlinebiddingproject.models.entities.*;
import com.ghtk.onlinebiddingproject.repositories.NotificationAuctionRepository;
import com.ghtk.onlinebiddingproject.repositories.NotificationTypeRepository;
import com.ghtk.onlinebiddingproject.security.UserDetailsImpl;
import com.ghtk.onlinebiddingproject.services.NotificationAuctionService;
import com.ghtk.onlinebiddingproject.utils.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationAuctionServiceImpl implements NotificationAuctionService {

    @Autowired
    private NotificationAuctionRepository notificationAuctionRepository;

    @Override
    public NotificationAuction getById(Integer id) {
        return notificationAuctionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy thông báo của bài đấu giá với id này"));
    }

    @Override
    public NotificationAuction save(Auction auction, Notification notification) {
        return null;
    }

    @Override
    public NotificationAuction getNotificationByAuctionId(Integer auctionId) {
        return notificationAuctionRepository.findNotificationByAuctionId(auctionId);
    }
}
