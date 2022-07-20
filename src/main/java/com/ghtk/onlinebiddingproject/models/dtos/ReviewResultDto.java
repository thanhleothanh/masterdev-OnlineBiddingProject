package com.ghtk.onlinebiddingproject.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ghtk.onlinebiddingproject.constants.ReviewResultConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResultDto {
    private Integer id;

    @NotBlank(message = "thiếu thông tin kết quả (REJECTED/ACCEPTED)!")
    private ReviewResultConstants result;

    @JsonIgnore
    @NotNull(message = "thiếu thông tin bài đấu giá!")
    private AuctionDto auction;

    @NotNull(message = "thiếu thông tin admin!")
    private AdminDto admin;
}
