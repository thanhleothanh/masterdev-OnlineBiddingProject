package com.ghtk.onlinebiddingproject.models.responses;

import com.ghtk.onlinebiddingproject.constants.AuctionStatusConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlainAuctionResponseDto {

    private Integer id;

    private String description;

    private LocalDateTime timeStart;

    private LocalDateTime timeEnd;

    private Double priceStart;

    private Double priceStep;

    private Double highestPrice;

    private AuctionStatusConstants status;

//    private WinnerDto winner;

}
