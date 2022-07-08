package com.ghtk.onlinebiddingproject.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ghtk.onlinebiddingproject.constants.ReportResultConstants;
import com.ghtk.onlinebiddingproject.models.entities.Report;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReportResultDto {
    private Integer id;

    @NotNull(message = "thiếu thông tin kết quả (REJECTED/ACCEPTED)!")
    private ReportResultConstants result;

    @JsonIgnore
    private Report report;

    @JsonIgnore
    private AdminDto admin;
}
