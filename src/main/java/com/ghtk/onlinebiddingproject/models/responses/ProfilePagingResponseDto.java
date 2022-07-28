package com.ghtk.onlinebiddingproject.models.responses;

import com.ghtk.onlinebiddingproject.models.dtos.ProfileDto;
import lombok.Data;

import java.util.List;

@Data
public class ProfilePagingResponseDto {
    private Integer count;
    private Integer page;
    private Integer pageSize;
    private Integer pageTotal;
    private List<ProfileDto> profiles;
}
