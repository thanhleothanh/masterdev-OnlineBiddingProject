package com.ghtk.onlinebiddingproject.models.responses;

import com.ghtk.onlinebiddingproject.models.dtos.NotificationDto;
import lombok.Data;

import java.util.List;

@Data
public class NotificationPagingResponseDto {
    private Integer count;
    private Integer page;
    private Integer pageSize;
    private Integer pageTotal;
    private List<NotificationDto> notifications;
}
