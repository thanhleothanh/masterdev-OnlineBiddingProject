package com.ghtk.onlinebiddingproject.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
    @NotNull(message = "thiếu thông tin tài khoản!")
    private Integer id;
}
