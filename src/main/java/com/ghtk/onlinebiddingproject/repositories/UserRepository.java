package com.ghtk.onlinebiddingproject.repositories;

import com.ghtk.onlinebiddingproject.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
