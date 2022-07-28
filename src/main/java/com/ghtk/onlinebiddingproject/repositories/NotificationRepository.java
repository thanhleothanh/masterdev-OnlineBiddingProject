package com.ghtk.onlinebiddingproject.repositories;

import com.ghtk.onlinebiddingproject.models.entities.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer>, JpaSpecificationExecutor<Notification> {
    Page<Notification> findByNotificationNotifieds_Profile_Id(Integer id, Pageable pageable);

    Notification findByNotificationAuction_Auction_IdAndNotificationType(Integer id, String notificationType);


    @Modifying
    @Query(value = "INSERT INTO notification_auction(`auction_id`, `notification_id`) VALUES (:auctionId , :notificationId)", nativeQuery = true)
    @Transactional
    void insertNotificationAuction(@Param("auctionId") Integer auctionId, @Param("notificationId") Integer notificationId);

    @Modifying
    @Query(value = "INSERT INTO notification_report(`report_id`, `notification_id`) VALUES (:reportId , :notificationId)", nativeQuery = true)
    @Transactional
    void insertNotificationReport(@Param("reportId") Integer reportId, @Param("notificationId") Integer notificationId);

}
