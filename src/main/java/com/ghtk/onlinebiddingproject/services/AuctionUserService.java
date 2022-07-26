package com.ghtk.onlinebiddingproject.services;

public interface AuctionUserService {

    boolean getInterestedAuction(Integer auctionId);

    void saveInterestedAuction(Integer auctionId);

    void removeInterestedAuction(Integer auctionId);
}
