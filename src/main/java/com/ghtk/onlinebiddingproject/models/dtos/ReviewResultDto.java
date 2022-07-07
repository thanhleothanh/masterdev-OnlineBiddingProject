package com.ghtk.onlinebiddingproject.models.dtos;

import com.ghtk.onlinebiddingproject.constants.ReviewResultConstants;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ReviewResultDto {
    private Integer id;

    @NotBlank(message = "thiếu thông tin kết quả (REJECTED/ACCEPTED)!")
    private ReviewResultConstants result;

    @NotNull(message = "thiếu thông tin bài đấu giá!")
    private AuctionDto auction;

    @NotNull(message = "thiếu thông tin admin!")
    private AdminDto admin;
}
