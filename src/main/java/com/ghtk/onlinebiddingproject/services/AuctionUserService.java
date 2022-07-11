package com.ghtk.onlinebiddingproject.services;

public interface AuctionUserService {

    void saveInterestUser(Integer auctionId);

    void removeInterestUser(Integer auctionId);
}
