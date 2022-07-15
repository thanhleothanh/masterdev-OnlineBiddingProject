package com.ghtk.onlinebiddingproject.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemImageDto {
    private Integer id;

    @NotBlank(message = "ảnh sản phẩm không được để trống!")
    private String imageUrl;

    @JsonIgnore
    private ItemDto item;
}
