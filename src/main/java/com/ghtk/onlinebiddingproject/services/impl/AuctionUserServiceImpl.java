package com.ghtk.onlinebiddingproject.services.impl;

import com.ghtk.onlinebiddingproject.exceptions.NotFoundException;
import com.ghtk.onlinebiddingproject.models.entities.Auction;
import com.ghtk.onlinebiddingproject.models.entities.AuctionUser;
import com.ghtk.onlinebiddingproject.models.entities.User;
import com.ghtk.onlinebiddingproject.repositories.AuctionUserRepository;
import com.ghtk.onlinebiddingproject.security.UserDetailsImpl;
import com.ghtk.onlinebiddingproject.services.AuctionUserService;
import com.ghtk.onlinebiddingproject.utils.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuctionUserServiceImpl implements AuctionUserService {
    @Autowired
    private AuctionUserRepository auctionUserRepository;

    @Override
    public boolean getInterestedAuction(Integer auctionId) {
        UserDetailsImpl userDetails = CurrentUserUtils.getCurrentUserDetails();
        boolean existedRecord = auctionUserRepository.existsByAuction_IdAndUser_Id(auctionId, userDetails.getId());
        if (!existedRecord) throw new NotFoundException("Người dùng chưa quan tâm auction này!");
        return existedRecord;
    }

    @Override
    public void saveInterestedAuction(Integer auctionId) {
        UserDetailsImpl userDetails = CurrentUserUtils.getCurrentUserDetails();
        boolean existedRecord = auctionUserRepository.existsByAuction_IdAndUser_Id(auctionId, userDetails.getId());
        if (!existedRecord) {
            Auction auction = new Auction(auctionId);
            User user = new User(userDetails.getId());
            AuctionUser auctionUser = new AuctionUser(auction, user);
            auctionUserRepository.save(auctionUser);
        }
    }

    @Override
    public void removeInterestedAuction(Integer auctionId) {
        UserDetailsImpl userDetails = CurrentUserUtils.getCurrentUserDetails();
        AuctionUser interestedUser = auctionUserRepository.findByAuction_IdAndUser_Id(auctionId, userDetails.getId());
        if (interestedUser != null) {
            auctionUserRepository.delete(interestedUser);
        }
    }
}
