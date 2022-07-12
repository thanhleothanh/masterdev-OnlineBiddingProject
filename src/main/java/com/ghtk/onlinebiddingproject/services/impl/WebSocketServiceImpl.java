package com.ghtk.onlinebiddingproject.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghtk.onlinebiddingproject.models.responses.CommonResponse;
import com.ghtk.onlinebiddingproject.services.WebSocketService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketServiceImpl implements WebSocketService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Override
    @SneakyThrows
    public void sendBid(Integer auctionId, CommonResponse commonResponse) {
        String dest = "/topic/auctions/" + auctionId + "/bids";
        String json = (new ObjectMapper()).writeValueAsString(commonResponse);
        simpMessagingTemplate.convertAndSend(dest, json);
    }
}
