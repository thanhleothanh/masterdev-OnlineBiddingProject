package com.ghtk.onlinebiddingproject.repositories;

import com.ghtk.onlinebiddingproject.constants.AuctionStatusConstants;
import com.ghtk.onlinebiddingproject.models.entities.Auction;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

public interface AuctionRepository extends JpaRepository<Auction, Integer>, JpaSpecificationExecutor<Auction> {
    List<Auction> findByUser_Id(Integer id, Sort sort);

    List<Auction> findByUser_IdAndStatus(Integer id, AuctionStatusConstants status, Sort sort);

    List<Auction> findByUser_IdAndStatusNotIn(Integer id, Collection<AuctionStatusConstants> statuses, Sort sort);

    @Modifying
    @Transactional
    @Query(value = "UPDATE auction" +
            "           SET status = 3" +
            "           WHERE auction.id = :auctionId ;", nativeQuery = true)
    void startAuction(Integer auctionId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE auction" +
            "           SET status = -1" +
            "           WHERE auction.id = :auctionId ;", nativeQuery = true)
    void cancelAuction(Integer auctionId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE auction " +
            "           SET status = 4" +
            "           WHERE auction.id = :auctionId ;", nativeQuery = true)
    void endAuction(Integer auctionId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO winner(`bid_id`, `auction_id`) SELECT A.bid_id, A.auction_id " +
            "           FROM (SELECT bid.id as bid_id, bid.auction_id as auction_id FROM bid" +
            "           WHERE bid.auction_id = :auctionId ORDER BY bid.price DESC LIMIT 1) A ;"
            , nativeQuery = true)
    void insertWinner(Integer auctionId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE auction" +
            "           SET status = 5" +
            "           WHERE auction.id = :auctionId ;", nativeQuery = true)
    void confirmAuction(Integer auctionId);


    /*
     * Timer Events
     * deprecated
     * */

    /*
     * cancel all pending auctions when time_start comes but still no admin approve
     */
//    CREATE EVENT IF NOT EXISTS cancelAuction
//    ON SCHEDULE EVERY 10 SECOND
//    STARTS CURRENT_TIMESTAMP
//    ENDS CURRENT_TIMESTAMP + INTERVAL 2 MONTH
//            DO
//    UPDATE auction
//    SET auction.status = -1
//    WHERE now()> auction.time_start and auction.status=1;

    /*
     * end openning auctions when time_end comes
     */
//    DELIMITER |
//    CREATE EVENT IF NOT EXISTS endAuctionAndInsertWinner
//    ON SCHEDULE EVERY 10 SECOND
//    STARTS CURRENT_TIMESTAMP
//    ENDS CURRENT_TIMESTAMP + INTERVAL 2 MONTH
//            DO
//    BEGIN
//    INSERT INTO `winner`(`bid_id`, `auction_id`) select A.bid_id, A.auction_id from (
//            select b.id as bid_id, a.id as auction_id  from bid b join auction a on b.auction_id = a.id
//            where now() > a.time_end and a.status=3 and (auction_id, price) IN (
//    select auction_id, max(price) from bid
//    group by bid.auction_id )) A;
//    UPDATE auction
//    SET auction.status = 4
//    WHERE NOW() > auction.time_end AND auction.status = 3;
//    END |
//    DELIMITER ;

    /*
     * open queued auctions queued -> opening
     */
//    CREATE EVENT IF NOT EXISTS openAuction
//    ON SCHEDULE EVERY 10 SECOND
//    STARTS CURRENT_TIMESTAMP
//    ENDS CURRENT_TIMESTAMP + INTERVAL 2 MONTH
//            DO
//    UPDATE auction
//    SET auction.status = 3
//    WHERE now()> auction.time_end and auction.status=2;
}
