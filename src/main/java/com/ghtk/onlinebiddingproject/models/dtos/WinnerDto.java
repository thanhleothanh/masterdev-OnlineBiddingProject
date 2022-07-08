package com.ghtk.onlinebiddingproject.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class WinnerDto {
    private Integer id;
    private BidDto bid;

    @JsonIgnore
    private AuctionDto auction;
}
