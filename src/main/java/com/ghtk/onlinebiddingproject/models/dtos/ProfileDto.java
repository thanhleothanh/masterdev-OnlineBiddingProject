package com.ghtk.onlinebiddingproject.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ghtk.onlinebiddingproject.constants.UserStatusConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    private Integer id;

    private String username;

    @JsonIgnore
    private String password;

    private String name;

    private String email;

    private String imageUrl;

    private UserStatusConstants status;

    @JsonIgnore
    private RoleDto role;

}
