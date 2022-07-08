package com.ghtk.onlinebiddingproject.services;

import com.ghtk.onlinebiddingproject.models.entities.Item;
import com.ghtk.onlinebiddingproject.models.entities.ItemImage;

public interface ItemService {


    Item save(Item item);


    ItemImage saveItemImage(Integer id, ItemImage bid);
}
