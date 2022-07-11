package com.ghtk.onlinebiddingproject.services.impl;

import com.ghtk.onlinebiddingproject.exceptions.NotFoundException;
import com.ghtk.onlinebiddingproject.models.entities.Auction;
import com.ghtk.onlinebiddingproject.models.entities.Item;
import com.ghtk.onlinebiddingproject.models.entities.ItemImage;
import com.ghtk.onlinebiddingproject.repositories.ItemImageRepository;
import com.ghtk.onlinebiddingproject.repositories.ItemRepository;
import com.ghtk.onlinebiddingproject.services.ItemService;
import com.ghtk.onlinebiddingproject.utils.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemImageRepository itemImageRepository;

    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public Item save(Item item) {
        Item newItem = itemRepository.save(item);
        List<ItemImage> itemImages = item.getItemImages();
        if (itemImages != null && itemImages.size() != 0) {
            for (ItemImage itemImage : itemImages) {
                itemImage.setItem(newItem);
                itemImageRepository.save(itemImage);
            }
        }
        return newItem;
    }

    /*
     * Item Image
     * */
    @Override
    public ItemImage saveItemImage(Integer id, ItemImage itemImage) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy item với id này!"));
        Auction auction = item.getAuction();
        boolean isPostedByCurrentUser = CurrentUserUtils.isPostedByCurrentUser(auction.getUser().getId());
        if (isPostedByCurrentUser) {
            itemImage.setItem(item);
            return itemImageRepository.save(itemImage);
        } else throw new AccessDeniedException("Không có quyền thêm ảnh vào sản phẩm này!");
    }
}
