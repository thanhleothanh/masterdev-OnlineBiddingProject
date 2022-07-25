package com.ghtk.onlinebiddingproject.services;

import com.ghtk.onlinebiddingproject.models.entities.Item;
import com.ghtk.onlinebiddingproject.models.entities.ItemImage;
import com.ghtk.onlinebiddingproject.models.requests.ItemRequestDto;

import java.io.IOException;

public interface ItemService {
    Item getById(Integer id);

    Item save(Item item);

    Item put(ItemRequestDto itemDto, Item item);

    ItemImage saveItemImage(Integer id, ItemImage itemImage);

    void deleteItemImage(Integer itemId, Integer id) throws IOException;
}
