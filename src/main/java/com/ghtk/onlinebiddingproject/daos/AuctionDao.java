package com.ghtk.onlinebiddingproject.daos;

import com.ghtk.onlinebiddingproject.models.responses.AuctionTopTrendingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuctionDao {
    private final EntityManager entityManager;

    public List<AuctionTopTrendingDto> getTopTrending() {
        String strQuery = "SELECT   auction.id as id," +
                "                   auction.user_id as user_id," +
                "                   auction.category_id as category_id," +
                "                   auction.description as description," +
                "                   auction.status as status," +
                "                   auction.time_start as time_start," +
                "                   auction.time_end as time_end," +
                "                   auction.price_start as price_start," +
                "                   auction.price_step as price_step," +
                "                   auction.highest_price as highest_price," +
                "                   item.id as itemId," +
                "                   item.name as itemName," +
                "                   item.description as itemDescription," +
                "                   item.amount as itemAmount," +
                "                   COUNT(bid.id) as numberOfBids" +
                "            FROM auction JOIN bid ON auction.id = bid.auction_id" +
                "                           JOIN item ON auction.id = item.auction_id" +
                "            WHERE auction.status = 3" +
                "            GROUP BY auction.id" +
                "            ORDER BY numberOfBids DESC";
        Query query = entityManager.createNativeQuery(strQuery, "AuctionTopTrendingDto");
        return query.getResultList();
    }
}