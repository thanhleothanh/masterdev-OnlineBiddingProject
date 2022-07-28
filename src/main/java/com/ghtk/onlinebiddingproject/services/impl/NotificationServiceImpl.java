package com.ghtk.onlinebiddingproject.services.impl;

import com.ghtk.onlinebiddingproject.constants.EntityTypeConstants;
import com.ghtk.onlinebiddingproject.constants.NotificationTypeConstants;
import com.ghtk.onlinebiddingproject.exceptions.NotFoundException;
import com.ghtk.onlinebiddingproject.models.entities.*;
import com.ghtk.onlinebiddingproject.repositories.NotificationRepository;
import com.ghtk.onlinebiddingproject.repositories.NotificationTypeRepository;
import com.ghtk.onlinebiddingproject.security.UserDetailsImpl;
import com.ghtk.onlinebiddingproject.services.NotificationService;
import com.ghtk.onlinebiddingproject.utils.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Notification getById(Integer id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy notification với id này"));
    }

    @Override
    public Notification adminSaveAuctionNotification(Auction auction, Notification notification) {
        UserDetailsImpl userDetails = CurrentUserUtils.getCurrentUserDetails();
        NotificationNotifier notificationNotifier = new NotificationNotifier(new Profile(userDetails.getId()));
        NotificationNotified notificationNotified = new NotificationNotified(new Profile(auction.getUser().getId()));
        notification.setNotificationTypeName(NotificationTypeConstants.ACCEPT_AUCTION.getDescription());
        notification.setNotificationNotifier(notificationNotifier);
        notification.setNotificationNotified(notificationNotified);
        return notificationRepository.save(notification);
    }


}
