package com.ghtk.onlinebiddingproject.models.requests;

import com.ghtk.onlinebiddingproject.constants.AuctionStatusConstants;
import com.ghtk.onlinebiddingproject.models.dtos.CategoryDto;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class AuctionRequestDto {
    private Integer id;

    @Size(max = 1000, message = "Mô tả không được quá 1000 ký tự!")
    private String description;

    private LocalDateTime timeStart;

    private LocalDateTime timeEnd;

    @Min(value = 0, message = "Giá khởi điểm không được nhỏ hơn hoặc bằng 0!")
    private Double priceStart;

    @Min(value = 0, message = "Bước giá không được nhỏ hơn hoặc bằng 0!")
    private Double priceStep;

    private CategoryDto category;

    private AuctionStatusConstants status;

    @Valid
    private ItemRequestDto item;
}
