package com.ghtk.onlinebiddingproject.models.responses;

import com.ghtk.onlinebiddingproject.models.entities.Notification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class NotificationPagingResponse {
    private Integer count;
    private Integer page;
    private Integer pageSize;
    private Integer pageTotal;
    private List<Notification> notifications;
}
