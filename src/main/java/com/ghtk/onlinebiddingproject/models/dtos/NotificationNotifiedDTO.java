package com.ghtk.onlinebiddingproject.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationNotifiedDTO {
    private Integer id;
    private Integer notificationId;
    private Integer notifiedId;
}
