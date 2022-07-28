package com.ghtk.onlinebiddingproject.services;

import com.ghtk.onlinebiddingproject.models.entities.Auction;
import com.ghtk.onlinebiddingproject.models.entities.Profile;
import com.ghtk.onlinebiddingproject.models.entities.Report;
import com.ghtk.onlinebiddingproject.models.responses.NotificationPagingResponse;
import org.springframework.http.HttpHeaders;

public interface NotificationService {
    NotificationPagingResponse getMyNotifications(HttpHeaders headers);

    void createReviewAuctionNotification(Profile profile, Auction auction);

    void createEndAuctionNotification(Auction auction);

    void createStartAuctionNotification(Auction auction);

    void createNewBidAuctionNotification(Auction auction);

    void saveNewBidAuctionNotification(Profile profile, Auction auction);

    void createNewAuctionUserNotification(Profile profile, Auction auction);

    void createNewReportNotification(Report report);

    void createJudgeReportNotification(Report report);
}
