package com.ghtk.onlinebiddingproject.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ReportImageDto {
    private Integer id;

    @NotBlank(message = "thiếu thông tin url ảnh!")
    private String imageUrl;

    @JsonIgnore
    private ReportDto report;
}
