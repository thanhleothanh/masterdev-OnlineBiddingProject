package com.ghtk.onlinebiddingproject.controllers;

import com.ghtk.onlinebiddingproject.models.dtos.AuctionDto;
import com.ghtk.onlinebiddingproject.models.dtos.NotificationDTO;
import com.ghtk.onlinebiddingproject.models.entities.Auction;
import com.ghtk.onlinebiddingproject.models.entities.Notification;
import com.ghtk.onlinebiddingproject.models.entities.NotificationAuction;
import com.ghtk.onlinebiddingproject.models.requests.AuctionRequestDto;
import com.ghtk.onlinebiddingproject.models.responses.CommonResponse;
import com.ghtk.onlinebiddingproject.services.AuctionService;
import com.ghtk.onlinebiddingproject.services.NotificationAuctionService;
import com.ghtk.onlinebiddingproject.services.NotificationService;
import com.ghtk.onlinebiddingproject.utils.converters.EntityToDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/notification")
public class NotificationController {
    @Autowired
    AuctionService auctionService;
    @Autowired
    NotificationService notificationService;

    @Autowired
    NotificationAuctionService notificationAuctionService;
    @Autowired
    EntityToDtoConverter entityToDtoConverter;

    @PutMapping("/{id}/approveAuction")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CommonResponse> adminSaveAuctionNotification(@PathVariable("id") int id) {
        Auction auction = auctionService.adminGetById(id);
        NotificationAuction notificationAuction = notificationAuctionService.getNotificationByAuctionId(id);
        Notification notification = notificationService.getById(notificationAuction.getId());
        Notification approvedAuctionNotification = notificationService.adminSaveAuctionNotification(auction,notification);

        NotificationDTO dtoResponse = entityToDtoConverter.convertToDto(approvedAuctionNotification);
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
