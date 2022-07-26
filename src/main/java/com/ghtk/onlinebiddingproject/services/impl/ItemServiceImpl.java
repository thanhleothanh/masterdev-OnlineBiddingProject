package com.ghtk.onlinebiddingproject.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ghtk.onlinebiddingproject.constants.AuctionStatusConstants;
import com.ghtk.onlinebiddingproject.exceptions.NotFoundException;
import com.ghtk.onlinebiddingproject.models.entities.Auction;
import com.ghtk.onlinebiddingproject.models.entities.Item;
import com.ghtk.onlinebiddingproject.models.entities.ItemImage;
import com.ghtk.onlinebiddingproject.models.requests.ItemRequestDto;
import com.ghtk.onlinebiddingproject.repositories.AuctionRepository;
import com.ghtk.onlinebiddingproject.repositories.ItemImageRepository;
import com.ghtk.onlinebiddingproject.repositories.ItemRepository;
import com.ghtk.onlinebiddingproject.security.UserDetailsImpl;
import com.ghtk.onlinebiddingproject.services.ItemService;
import com.ghtk.onlinebiddingproject.utils.CurrentUserUtils;
import com.ghtk.onlinebiddingproject.utils.DtoToEntityUtils;
import lombok.SneakyThrows;
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
    private AuctionRepository auctionRepository;
    @Autowired
    private ItemImageRepository itemImageRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Item getById(Integer id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy item với id này!"));
    }

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

    @Override
    public Item put(ItemRequestDto itemDto, Integer id) {
        Item item = getById(id);
        Auction auction = auctionRepository.findById(item.getAuction().getId())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy auction với id này!"));
        AuctionStatusConstants currentStatus = auction.getStatus();

        UserDetailsImpl userDetails = CurrentUserUtils.getCurrentUserDetails();
        if (userDetails.isSuspended()) throw new AccessDeniedException("Tài khoản của bạn đang bị giới hạn!");
        boolean isPostedByCurrentUser = CurrentUserUtils.isPostedByCurrentUser(auction.getUser().getId());

        if (!isPostedByCurrentUser)
            throw new AccessDeniedException("Chỉ admin và chủ bài đấu giá mới có quyền sửa!");
        if (!currentStatus.equals(AuctionStatusConstants.DRAFT) && !currentStatus.equals(AuctionStatusConstants.PENDING))
            throw new AccessDeniedException("Không thể thực hiện sửa bài đấu giá khi đã và đang (chờ) đấu giá!");

        DtoToEntityUtils.copyNonNullProperties(itemDto, item);
        return itemRepository.save(item);
    }

    /*
     * Item Image
     * */

    @Override
    public ItemImage saveItemImage(Integer id, ItemImage itemImage) {
        Item item = getById(id);
        Auction auction = item.getAuction();
        boolean isPostedByCurrentUser = CurrentUserUtils.isPostedByCurrentUser(auction.getUser().getId());
        if (isPostedByCurrentUser) {
            itemImage.setItem(item);
            return itemImageRepository.save(itemImage);
        } else throw new AccessDeniedException("Không có quyền thêm ảnh vào sản phẩm này!");
    }

    @Override
    @SneakyThrows
    public void deleteItemImage(Integer itemId, Integer id) {
        Item item = getById(itemId);
        Auction auction = auctionRepository.findById(item.getAuction().getId())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy auction với id này!"));
        AuctionStatusConstants currentStatus = auction.getStatus();

        UserDetailsImpl userDetails = CurrentUserUtils.getCurrentUserDetails();
        if (userDetails.isSuspended()) throw new AccessDeniedException("Tài khoản của bạn đang bị giới hạn!");
        boolean isPostedByCurrentUser = CurrentUserUtils.isPostedByCurrentUser(auction.getUser().getId());

        if (!isPostedByCurrentUser)
            throw new AccessDeniedException("Chỉ admin và chủ bài đấu giá mới có quyền sửa!");
        if (!currentStatus.equals(AuctionStatusConstants.DRAFT) && !currentStatus.equals(AuctionStatusConstants.PENDING))
            throw new AccessDeniedException("Không thể thực hiện sửa bài đấu giá khi đã và đang (chờ) đấu giá!");

        ItemImage itemImage = itemImageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy item image với id này!"));
        if (itemImage.getPublicId() != null)
            cloudinary.uploader().destroy(itemImage.getPublicId(), ObjectUtils.emptyMap());

        itemImageRepository.delete(itemImage);
    }
}
