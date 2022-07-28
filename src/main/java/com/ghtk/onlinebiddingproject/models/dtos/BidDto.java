package com.ghtk.onlinebiddingproject.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BidDto {
    private Integer id;

    @NotNull(message = "Thiếu thông tin giá bid!")
    @Positive(message = "Giá không được nhỏ hơn hoặc bằng 0!")
    private Double price;

    private UserDto user;

    private String createdAt;
}
