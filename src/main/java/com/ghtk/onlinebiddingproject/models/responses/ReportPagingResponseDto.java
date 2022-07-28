package com.ghtk.onlinebiddingproject.models.responses;

import com.ghtk.onlinebiddingproject.models.dtos.ReportDto;
import lombok.Data;

import java.util.List;

@Data
public class ReportPagingResponseDto {
    private Integer count;
    private Integer page;
    private Integer pageSize;
    private Integer pageTotal;
    private List<ReportDto> reports;
}
