package com.ghtk.onlinebiddingproject.models.requests;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserLogin {

    @NotBlank(message = "username không được để trống!")
    @Pattern(regexp = "[a-zA-Z0-9_.]*")
    @Length(min = 5, max = 30, message = "username có độ dài từ 5 - 30 ký tự!")
    private String username;

    @NotBlank(message = "password không được để trống!")
    private String password;
}
