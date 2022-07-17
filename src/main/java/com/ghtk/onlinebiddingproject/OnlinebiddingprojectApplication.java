package com.ghtk.onlinebiddingproject;

import com.ghtk.onlinebiddingproject.models.dtos.AuctionDto;
import com.ghtk.onlinebiddingproject.models.dtos.BidDto;
import com.ghtk.onlinebiddingproject.models.entities.Auction;
import com.ghtk.onlinebiddingproject.models.entities.Bid;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OnlinebiddingprojectApplication {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        PropertyMap<Bid, BidDto> localDateTimeToStringBid = new PropertyMap<Bid, BidDto>() {
            protected void configure() {
                map().setCreatedAt(String.valueOf(source.getCreatedAt()));
            }
        };
        PropertyMap<Auction, AuctionDto> localDateTimeToStringAuction = new PropertyMap<Auction, AuctionDto>() {
            protected void configure() {
                map().setCreatedAt(String.valueOf(source.getCreatedAt()));
            }
        };
        modelMapper.addMappings(localDateTimeToStringBid);
        modelMapper.addMappings(localDateTimeToStringAuction);
        return modelMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(OnlinebiddingprojectApplication.class, args);
    }
}
