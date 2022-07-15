package com.ghtk.onlinebiddingproject.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuctionUserDto {
    private Integer id;

    @NotNull(message = "thiếu thông tin user")
    private UserDto user;
}
