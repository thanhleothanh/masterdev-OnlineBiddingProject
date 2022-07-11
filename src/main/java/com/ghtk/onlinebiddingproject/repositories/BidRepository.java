package com.ghtk.onlinebiddingproject.repositories;

import com.ghtk.onlinebiddingproject.models.entities.Bid;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Integer> {
    List<Bid> findByUser_Id(Integer id);

    Bid findFirstByAuction_IdOrderByPriceDesc(Integer id);

    List<Bid> findByAuction_Id(Integer id, Sort sort);


}
