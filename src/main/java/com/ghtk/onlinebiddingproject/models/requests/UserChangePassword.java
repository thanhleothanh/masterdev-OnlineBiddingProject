package com.ghtk.onlinebiddingproject.models.requests;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class UserChangePassword {
    @NotBlank(message = "password hiện tại không được để trống!")
    private String currentPassword;

    @NotBlank(message = "password mới không được để trống!")
    @Length(min = 8, message = "password mới phải ít nhất 8 ký tự!")
    private String newPassword;

    @NotBlank(message = "xác nhận lại password mới!")
    private String confirmedNewPassword;
}
