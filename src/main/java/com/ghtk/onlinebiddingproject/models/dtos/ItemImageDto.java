package com.ghtk.onlinebiddingproject.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ItemImageDto {
    private Integer id;

    @NotBlank(message = "ảnh sản phẩm không được để trống!")
    private String imageUrl;

    @JsonIgnore
    private ItemDto item;
}
