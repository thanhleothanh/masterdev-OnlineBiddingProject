package com.ghtk.onlinebiddingproject.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ghtk.onlinebiddingproject.models.entities.NotificationNotified;
import com.ghtk.onlinebiddingproject.models.entities.NotificationNotifier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    private Integer id;
    @JsonIgnore
    private NotificationNotifier notificationNotifier;
    private String notificationTypeName;
    @JsonIgnore
    private NotificationNotified notificationNotified;
    private String createdAt;
}
