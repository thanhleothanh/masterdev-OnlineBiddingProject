package com.ghtk.onlinebiddingproject.models.requests;

import com.ghtk.onlinebiddingproject.models.dtos.ItemImageDto;
import com.ghtk.onlinebiddingproject.models.entities.Auction;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ItemRequestDto {

    private Integer id;

    @Size(max = 200, message = "tên sản phẩm không được quá 200 ký tự!")
    private String name;

    @Size(max = 1000, message = "mô tả sản phẩm không được quá 200 ký tự!")
    private String description;

    @Positive(message = "số lượng sản phẩm không được nhỏ hơn hoặc bằng 0!")
    private Integer amount;

    @NotNull(message = "Thiếu thông tin bài đấu giá!")
    private Auction auction;

    private List<ItemImageDto> itemImages;
}
