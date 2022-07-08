package com.ghtk.onlinebiddingproject.models.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ghtk.onlinebiddingproject.constants.AuctionStatusConstants;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class AuctionDto {
    private Integer id;

    @Size(max = 1000, message = "Mô tả không được quá 1000 ký tự!")
    private String description;

    private LocalDateTime timeStart;

    private LocalDateTime timeEnd;

    @Min(value = 0, message = "Giá khởi điểm không được nhỏ hơn hoặc bằng 0!")
    private Double priceStart;

    @Min(value = 0, message = "Bước giá không được nhỏ hơn hoặc bằng 0!")
    private Double priceStep;

    @Min(value = 0, message = "Giá cao nhất không được nhỏ hơn hoặc bằng 0!")
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
