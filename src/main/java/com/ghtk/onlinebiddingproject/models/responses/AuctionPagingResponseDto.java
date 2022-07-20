package com.ghtk.onlinebiddingproject.models.responses;

import com.ghtk.onlinebiddingproject.models.dtos.AuctionDto;
import lombok.Data;

import java.util.List;

@Data
public class AuctionPagingResponseDto {
    private Integer count;
    private Integer page;
    private Integer pageSize;
    private Integer pageTotal;
    private List<AuctionDto> auctions;
}
