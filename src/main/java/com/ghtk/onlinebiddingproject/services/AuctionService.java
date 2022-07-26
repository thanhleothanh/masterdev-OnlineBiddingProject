package com.ghtk.onlinebiddingproject.services;

import com.ghtk.onlinebiddingproject.constants.AuctionStatusConstants;
import com.ghtk.onlinebiddingproject.models.entities.Auction;
import com.ghtk.onlinebiddingproject.models.entities.Item;
import com.ghtk.onlinebiddingproject.models.requests.AuctionRequestDto;
import com.ghtk.onlinebiddingproject.models.responses.AuctionPagingResponse;
import com.ghtk.onlinebiddingproject.models.responses.AuctionTopTrendingDto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface AuctionService {
    AuctionPagingResponse get(Specification<Auction> spec, HttpHeaders headers, Sort sort);

    List<AuctionTopTrendingDto> getTopTrending();

    List<Auction> getMyAuctions(AuctionStatusConstants status);

    List<Auction> getWonAuctionsByUserId(Integer userId);

    List<Auction> getAuctionsByUserId(Integer userId);

    List<Auction> getMyInterestedAuctions();

    Auction getById(Integer id);

    Auction save(AuctionRequestDto auctionDto, Auction auction, Item item);

    Auction put(AuctionRequestDto auctionDto, Integer id);

    Auction submitPending(Integer id);

//    Auction confirmAuction(AuctionRequestDto auctionRequestDto, Integer id);

    void deleteById(Integer id);

    /*
     * For admin
     * */

    Auction adminGetById(Integer id);

    Auction adminPut(AuctionRequestDto auctionDto, Integer id);

    Auction adminReviewSubmit(AuctionRequestDto auctionRequestDto, Integer id);

    void adminDeleteById(Integer id);

}
