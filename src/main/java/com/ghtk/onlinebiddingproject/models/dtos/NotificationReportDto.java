package com.ghtk.onlinebiddingproject.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationReportDto {
    private Integer id;

    @JsonIgnore
    private NotificationDto notification;

    private ReportDto report;
}
