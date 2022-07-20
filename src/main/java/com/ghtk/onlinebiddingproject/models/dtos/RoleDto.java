package com.ghtk.onlinebiddingproject.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private Integer id;

    @NotBlank(message = "tên vai trò không được để trống!")
    private String name;
}
