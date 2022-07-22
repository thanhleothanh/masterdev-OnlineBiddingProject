package com.ghtk.onlinebiddingproject.services;

import com.ghtk.onlinebiddingproject.models.entities.Auction;

public interface JobSchedulerService {
    /*
     * Quartz
     * */

    void startAuctionScheduler(Auction auction);

    void endAuctionScheduler(Auction auction);

    void cancelAuctionScheduler(Auction auction);
}
