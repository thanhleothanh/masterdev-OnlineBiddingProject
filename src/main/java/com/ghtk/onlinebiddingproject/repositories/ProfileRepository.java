package com.ghtk.onlinebiddingproject.repositories;

import com.ghtk.onlinebiddingproject.models.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Integer>, JpaSpecificationExecutor<Profile> {
    Optional<Profile> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Modifying
    @Query(value = "INSERT INTO user(`profile_id`) VALUES (:profileId)", nativeQuery = true)
    @Transactional
    void insertUser(@Param("profileId") Integer profileId);

    @Modifying
    @Query(value = "INSERT INTO admin(`profile_id`) VALUES (:profileId)", nativeQuery = true)
    @Transactional
    void insertAdmin(@Param("profileId") Integer profileId);
}
