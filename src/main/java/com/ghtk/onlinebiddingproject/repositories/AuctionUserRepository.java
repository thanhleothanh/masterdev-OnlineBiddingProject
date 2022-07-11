package com.ghtk.onlinebiddingproject.repositories;

import com.ghtk.onlinebiddingproject.models.entities.AuctionUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionUserRepository extends JpaRepository<AuctionUser, Integer> {
    AuctionUser findByAuction_IdAndUser_Id(Integer auctionId, Integer userId);

}
