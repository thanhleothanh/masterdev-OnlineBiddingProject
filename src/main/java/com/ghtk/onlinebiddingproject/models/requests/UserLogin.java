package com.ghtk.onlinebiddingproject.models.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserLogin {

    @NotBlank(message = "username không được để trống!")
    @Pattern(regexp = "[a-zA-Z0-9_.]*")
    private String username;

    @NotBlank(message = "password không được để trống!")
    private String password;
}
