package com.ghtk.onlinebiddingproject.services;

import com.ghtk.onlinebiddingproject.models.entities.Auction;
import com.ghtk.onlinebiddingproject.models.entities.Profile;

import java.util.List;

public interface AuctionUserService {

    List<Auction> getMyInterestedAuctions();

    List<Profile> getInterestedUsersByAuctionId(Integer auctionId);

    void saveInterestedAuction(Integer auctionId);
}
