package com.ghtk.onlinebiddingproject.models.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationAuctionDTO {
    private Integer id;
    private Integer notificationId;
    private Integer auctionId;
}
