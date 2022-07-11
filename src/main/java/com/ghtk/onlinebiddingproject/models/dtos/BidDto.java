package com.ghtk.onlinebiddingproject.models.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class BidDto {
    private Integer id;

    @NotNull(message = "Thiếu thông tin giá bid!")
    @Positive(message = "Giá không được nhỏ hơn hoặc bằng 0!")
    private Double price;

    private UserDto user;
}
