package com.ghtk.onlinebiddingproject.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {
    private Integer id;

    @NotBlank(message = "thiếu thông tin mô tả!")
    private String description;

    @NotNull(message = "thiếu thông tin người báo cáo!")
    private UserDto userReporter;

    @NotNull(message = "thiếu thông tin người bị báo cáo!")
    private UserDto userReported;

    @JsonIgnore
    private AuctionDto auction;

    private ReportResultDto reportResult;

    private List<ReportImageDto> reportImages;
}
