package com.ghtk.onlinebiddingproject.services;

import com.ghtk.onlinebiddingproject.models.dtos.BidDto;
import com.ghtk.onlinebiddingproject.models.responses.CommonResponse;

public interface WebSocketService {

    void sendBid(Integer auctionId, CommonResponse commonResponse);
}
