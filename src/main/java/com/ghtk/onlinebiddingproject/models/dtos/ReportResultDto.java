package com.ghtk.onlinebiddingproject.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ghtk.onlinebiddingproject.constants.ReportResultConstants;
import com.ghtk.onlinebiddingproject.models.entities.Report;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportResultDto {
    private Integer id;

    @NotNull(message = "thiếu thông tin kết quả (REJECTED/ACCEPTED)!")
    private ReportResultConstants result;

    @JsonIgnore
    private Report report;

    @JsonIgnore
    private AdminDto admin;
}
