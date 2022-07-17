package com.ghtk.onlinebiddingproject.controllers;

import com.ghtk.onlinebiddingproject.models.dtos.ReportDto;
import com.ghtk.onlinebiddingproject.models.dtos.ReportImageDto;
import com.ghtk.onlinebiddingproject.models.entities.Report;
import com.ghtk.onlinebiddingproject.models.entities.ReportImage;
import com.ghtk.onlinebiddingproject.models.responses.CommonResponse;
import com.ghtk.onlinebiddingproject.services.impl.ReportServiceImpl;
import com.ghtk.onlinebiddingproject.utils.converters.DtoToEntityConverter;
import com.ghtk.onlinebiddingproject.utils.converters.EntityToDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/reports")
public class ReportController {
    @Autowired
    private ReportServiceImpl reportService;
    @Autowired
    private DtoToEntityConverter dtoToEntityConverter;
    @Autowired
    private EntityToDtoConverter entityToDtoConverter;

    @PostMapping("")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CommonResponse> save(@Validated @RequestBody ReportDto reportDto) {
        Report report = dtoToEntityConverter.convertToEntity(reportDto);

        ReportDto dtoResponse = entityToDtoConverter.convertToDto(reportService.save(report));
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/reportImages")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CommonResponse> postReportImage(@PathVariable Integer id, @Validated @RequestBody ReportImageDto reportImageDto) {
        ReportImage reportImage = dtoToEntityConverter.convertToEntity(reportImageDto);

        ReportImageDto dtoResponse = entityToDtoConverter.convertToDto(reportService.saveReportImage(id, reportImage));
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
