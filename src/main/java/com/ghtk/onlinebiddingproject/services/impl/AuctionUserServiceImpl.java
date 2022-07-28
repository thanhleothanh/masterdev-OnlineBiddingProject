package com.ghtk.onlinebiddingproject.services.impl;

import com.ghtk.onlinebiddingproject.constants.AuctionStatusConstants;
import com.ghtk.onlinebiddingproject.models.entities.Auction;
import com.ghtk.onlinebiddingproject.models.entities.AuctionUser;
import com.ghtk.onlinebiddingproject.models.entities.Profile;
import com.ghtk.onlinebiddingproject.models.entities.User;
import com.ghtk.onlinebiddingproject.repositories.AuctionUserRepository;
import com.ghtk.onlinebiddingproject.security.UserDetailsImpl;
import com.ghtk.onlinebiddingproject.services.AuctionUserService;
import com.ghtk.onlinebiddingproject.utils.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuctionUserServiceImpl implements AuctionUserService {
    @Autowired
    private AuctionUserRepository auctionUserRepository;
    @Autowired
    private NotificationServiceImpl notificationService;

    @Override
    public List<Auction> getMyInterestedAuctions() {
        UserDetailsImpl userDetails = CurrentUserUtils.getCurrentUserDetails();
        List<AuctionUser> auctionUsers = auctionUserRepository.findByUser_IdAndAuction_Status(userDetails.getId(), AuctionStatusConstants.OPENING);
        return auctionUsers.stream().map(AuctionUser::getAuction).collect(Collectors.toList());
    }

    @Override
    public List<Profile> getInterestedUsersByAuctionId(Integer auctionId) {
        List<AuctionUser> auctionUsers = auctionUserRepository.findByAuction_Id(auctionId);
        return auctionUsers.stream().map(AuctionUser::getUser).map(User::getProfile).collect(Collectors.toList());
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
            notificationService.createNewAuctionUserNotification(new Profile(user.getId()), auction);
        }
    }
}
