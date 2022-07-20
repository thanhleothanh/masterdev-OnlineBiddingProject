package com.ghtk.onlinebiddingproject.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    private Integer id;

    private String name;

    private String description;

    private Integer amount;

    private List<ItemImageDto> itemImages;
}
