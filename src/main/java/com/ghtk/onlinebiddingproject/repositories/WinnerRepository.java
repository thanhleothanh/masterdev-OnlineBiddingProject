package com.ghtk.onlinebiddingproject.repositories;

import com.ghtk.onlinebiddingproject.models.entities.Winner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WinnerRepository extends JpaRepository<Winner, Integer> {

}
