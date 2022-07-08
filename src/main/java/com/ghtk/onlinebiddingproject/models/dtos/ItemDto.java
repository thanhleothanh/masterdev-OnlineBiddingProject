package com.ghtk.onlinebiddingproject.models.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ItemDto {

    private Integer id;

    private String name;

    private String description;

    private Integer amount;

    private List<ItemImageDto> itemImages;
}
