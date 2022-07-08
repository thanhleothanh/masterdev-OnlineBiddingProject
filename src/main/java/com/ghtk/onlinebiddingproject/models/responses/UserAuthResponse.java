package com.ghtk.onlinebiddingproject.models.responses;

import com.ghtk.onlinebiddingproject.constants.UserStatusConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthResponse {
    private Integer id;
    private String username;
    private String name;
    private String email;
    private String imageUrl;
    private UserStatusConstants status;
}
