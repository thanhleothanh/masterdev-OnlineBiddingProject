package com.ghtk.onlinebiddingproject.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportImageDto {
    private Integer id;

    @NotBlank(message = "thiếu thông tin url ảnh!")
    private String imageUrl;

    private String publicId;

    @JsonIgnore
    private ReportDto report;
}
