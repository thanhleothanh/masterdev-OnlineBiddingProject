package com.ghtk.onlinebiddingproject.models.requests;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class UserChangeProfile {
    private String name;

    @Email(message = "Email không hợp lệ!")
    private String email;

    private String imageUrl;
}
