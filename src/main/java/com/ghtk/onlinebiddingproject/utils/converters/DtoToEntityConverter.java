package com.ghtk.onlinebiddingproject.utils.converters;

import com.ghtk.onlinebiddingproject.models.dtos.*;
import com.ghtk.onlinebiddingproject.models.entities.*;
import com.ghtk.onlinebiddingproject.models.requests.AuctionRequestDto;
import com.ghtk.onlinebiddingproject.models.requests.BidRequestDto;
import com.ghtk.onlinebiddingproject.models.requests.ItemRequestDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

@Component
public class DtoToEntityConverter {

    @Autowired
    private ModelMapper modelMapper;

    public Auction convertToEntity(AuctionDto auctionDto) throws ParseException {
        return modelMapper.map(auctionDto, Auction.class);
    }

    public Auction convertToEntity(AuctionRequestDto auctionDto) throws ParseException {
        return modelMapper.map(auctionDto, Auction.class);
    }

    public Item convertToEntity(ItemRequestDto itemDto) throws ParseException {
        return modelMapper.map(itemDto, Item.class);
    }

    public Report convertToEntity(ReportDto reportDto) throws ParseException {
        return modelMapper.map(reportDto, Report.class);
    }

    public ReportResult convertToEntity(ReportResultDto reportResultDto) throws ParseException {
        return modelMapper.map(reportResultDto, ReportResult.class);
    }

    public ItemImage convertToEntity(ItemImageDto itemImageDto) throws ParseException {
        return modelMapper.map(itemImageDto, ItemImage.class);
    }

    public ReportImage convertToEntity(ReportImageDto reportImageDto) throws ParseException {
        return modelMapper.map(reportImageDto, ReportImage.class);
    }

    public Bid convertToEntity(BidRequestDto bidDto) throws ParseException {
        return modelMapper.map(bidDto, Bid.class);
    }
}
