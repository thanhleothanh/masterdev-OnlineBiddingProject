package com.ghtk.onlinebiddingproject.models.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ghtk.onlinebiddingproject.constants.AuctionStatusConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuctionDto {
    private Integer id;

    private String description;

    private LocalDateTime timeStart;

    private LocalDateTime timeEnd;

    private Double priceStart;

    private Double priceStep;

    private Double highestPrice;

    private AuctionStatusConstants status;

    private UserDto user;

    private CategoryDto category;

    private ItemDto item;

    private WinnerDto winner;

    @JsonIgnore
    private ReviewResultDto reviewResultDto;

    @JsonIgnore
    private List<BidDto> bids;
}
