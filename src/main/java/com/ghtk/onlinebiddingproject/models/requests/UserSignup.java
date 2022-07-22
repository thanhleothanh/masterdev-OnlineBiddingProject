package com.ghtk.onlinebiddingproject.models.requests;

import com.ghtk.onlinebiddingproject.models.dtos.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignup {

    @NotBlank(message = "username không được để trống!")
    @Pattern(regexp = "[a-zA-Z0-9_.]*", message = "username không được chứa ký tự đặc biệt!")
    @Length(min = 5, max = 30, message = "username có độ dài từ 5 - 30 ký tự!")
    private String username;

    @NotBlank(message = "password không được để trống!")
    @Length(min = 8, message = "password phải ít nhất 8 ký tự!")
    private String password;

    @NotBlank(message = "tên người dùng không được để trống!")
    @Pattern(regexp = "^[A-Za-z]*$", message = "tên người dùng không được chứa ký tự đặc biệt hoặc số!")
    private String name;

    @Email(message = "email không hợp lệ!")
    private String email;

    @NotNull(message = "role người dùng không được để trống!")
    private RoleDto role;
}
