package com.ghtk.onlinebiddingproject.services.impl;

import com.ghtk.onlinebiddingproject.constants.AuctionStatusConstants;
import com.ghtk.onlinebiddingproject.exceptions.BadRequestException;
import com.ghtk.onlinebiddingproject.exceptions.NotFoundException;
import com.ghtk.onlinebiddingproject.models.entities.Auction;
import com.ghtk.onlinebiddingproject.models.entities.Bid;
import com.ghtk.onlinebiddingproject.models.entities.User;
import com.ghtk.onlinebiddingproject.models.requests.BidRequestDto;
import com.ghtk.onlinebiddingproject.repositories.AuctionRepository;
import com.ghtk.onlinebiddingproject.repositories.BidRepository;
import com.ghtk.onlinebiddingproject.security.UserDetailsImpl;
import com.ghtk.onlinebiddingproject.services.BidService;
import com.ghtk.onlinebiddingproject.utils.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class BidServiceImpl implements BidService {

    @Autowired
    private BidRepository bidRepository;
    @Autowired
    private AuctionRepository auctionRepository;
    @Autowired
    private AuctionUserServiceImpl auctionUserService;


    @Override
    public List<Bid> getBidsByAuctionId(Integer auctionId) {
        return bidRepository.findByAuction_Id(auctionId, Sort.by(Sort.Direction.DESC, "price"));
    }

    @Override
    public Bid getHighestBidByAuctionId(Integer id) {
        return bidRepository.findFirstByAuction_IdOrderByPriceDesc(id);
    }

    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public Bid saveBid(Integer auctionId, BidRequestDto bidDto, Bid bid) {
        UserDetailsImpl userDetails = CurrentUserUtils.getCurrentUserDetails();
        if (userDetails.isSuspended()) throw new AccessDeniedException("Tài khoản của bạn đang bị giới hạn!");

        User currentUser = new User(userDetails.getId());
        Auction auction = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy auction với id này!"));

        if (auction.getUser().getId().equals(userDetails.getId())) {
            throw new AccessDeniedException("Bạn không thể trả giá cho bài đấu giá của chính mình!");
        }
        if (!auction.getStatus().equals(AuctionStatusConstants.OPENING)) {
            throw new AccessDeniedException("Hiện tại không thể trả giá cho bài đấu giá này!");
        }
        if (bidDto.getPrice() < auction.getPriceStart()) {
            throw new BadRequestException("Giá mới phải cao hơn giá khởi điểm và giá hiện tại!");
        }
        if (bidDto.getPrice() <= auction.getHighestPrice()) {
            throw new BadRequestException("Giá mới phải cao hơn giá cao nhất hiện tại + bước giá!");
        }
        if (bidDto.getPrice() - auction.getHighestPrice() < auction.getPriceStep()) {
            throw new BadRequestException("Giá mới phải cao hơn giá cao nhất hiện tại + bước giá!");
        }


        auction.setHighestPrice(bidDto.getPrice());
        auctionRepository.save(auction);
        auctionUserService.saveInterestUser(auctionId);
        bid.setUser(currentUser);
        bid.setAuction(auction);
        return bidRepository.save(bid);
    }
}