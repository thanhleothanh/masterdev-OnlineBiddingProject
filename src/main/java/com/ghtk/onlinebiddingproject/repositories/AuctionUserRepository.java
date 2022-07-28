package com.ghtk.onlinebiddingproject.repositories;

import com.ghtk.onlinebiddingproject.constants.AuctionStatusConstants;
import com.ghtk.onlinebiddingproject.models.entities.AuctionUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuctionUserRepository extends JpaRepository<AuctionUser, Integer> {
    AuctionUser findByAuction_IdAndUser_Id(Integer auctionId, Integer userId);

    boolean existsByAuction_IdAndUser_Id(Integer auctionId, Integer userId);

    List<AuctionUser> findByUser_IdAndAuction_Status(Integer id, AuctionStatusConstants status);

    List<AuctionUser> findByAuction_Id(Integer id);

}
