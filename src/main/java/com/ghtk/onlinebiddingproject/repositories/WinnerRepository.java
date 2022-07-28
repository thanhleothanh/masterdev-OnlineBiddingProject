package com.ghtk.onlinebiddingproject.repositories;

import com.ghtk.onlinebiddingproject.models.entities.Winner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WinnerRepository extends JpaRepository<Winner, Integer> {
    List<Winner> findByBid_User_Id(Integer userId);

}
