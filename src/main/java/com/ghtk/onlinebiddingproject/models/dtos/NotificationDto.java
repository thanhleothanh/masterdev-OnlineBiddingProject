package com.ghtk.onlinebiddingproject.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {
    private Integer id;

    private ProfileDto profile;

    private String notificationType;

    private String entityType;

    @JsonIgnore
    private List<NotificationNotifiedDto> notificationNotifieds;

    private NotificationAuctionDto notificationAuction;

    private NotificationReportDto notificationReport;

    private String updatedAt;
}
