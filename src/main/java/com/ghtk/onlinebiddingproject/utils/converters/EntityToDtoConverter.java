package com.ghtk.onlinebiddingproject.utils.converters;

import com.ghtk.onlinebiddingproject.models.dtos.*;
import com.ghtk.onlinebiddingproject.models.entities.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntityToDtoConverter {
    @Autowired
    private ModelMapper modelMapper;

    public AuctionDto convertToDto(Auction auction) {
        return modelMapper.map(auction, AuctionDto.class);
    }

    public ItemDto convertToDto(Item item) {
        return modelMapper.map(item, ItemDto.class);
    }

    public ItemImageDto convertToDto(ItemImage itemImage) {
        return modelMapper.map(itemImage, ItemImageDto.class);
    }

    public ReportImageDto convertToDto(ReportImage reportImage) {
        return modelMapper.map(reportImage, ReportImageDto.class);
    }

    public ReportDto convertToDto(Report report) {
        return modelMapper.map(report, ReportDto.class);
    }

    public ReportResultDto convertToDto(ReportResult reportResult) {
        return modelMapper.map(reportResult, ReportResultDto.class);
    }

    public BidDto convertToBidDto(Bid bid) {
        return modelMapper.map(bid, BidDto.class);
    }

    public UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public ProfileDto convertToDto(Profile profile) {
        return modelMapper.map(profile, ProfileDto.class);
    }

    public List<AuctionDto> convertToListAuctionDto(List<Auction> auctions) {
        List<AuctionDto> listDto = new ArrayList<>();
        for (Auction auction : auctions) {
            listDto.add(modelMapper.map(auction, AuctionDto.class));
        }
        return listDto;
    }

    public List<BidDto> convertToListBidDto(List<Bid> bids) {
        List<BidDto> listDto = new ArrayList<>();
        for (Bid bid : bids) {
            listDto.add(modelMapper.map(bid, BidDto.class));
        }
        return listDto;
    }

    public List<ReportDto> convertToListReportDto(List<Report> reports) {
        List<ReportDto> listDto = new ArrayList<>();
        for (Report bid : reports) {
            listDto.add(modelMapper.map(bid, ReportDto.class));
        }
        return listDto;
    }
}
