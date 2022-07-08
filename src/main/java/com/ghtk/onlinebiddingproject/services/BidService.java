package com.ghtk.onlinebiddingproject.services;

import com.ghtk.onlinebiddingproject.models.entities.Bid;
import com.ghtk.onlinebiddingproject.models.requests.BidRequestDto;

import java.util.List;

public interface BidService {

    List<Bid> getBidsByAuctionId(Integer id);

    Bid getHighestBidByAuctionId(Integer id);

    Bid saveBid(Integer auctionId, BidRequestDto bidDto, Bid bid);
}
