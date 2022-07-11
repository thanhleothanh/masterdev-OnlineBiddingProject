package com.ghtk.onlinebiddingproject.controllers;

import com.ghtk.onlinebiddingproject.models.dtos.ReportDto;
import com.ghtk.onlinebiddingproject.models.dtos.ReportResultDto;
import com.ghtk.onlinebiddingproject.models.entities.Report;
import com.ghtk.onlinebiddingproject.models.entities.ReportResult;
import com.ghtk.onlinebiddingproject.models.responses.CommonResponse;
import com.ghtk.onlinebiddingproject.services.impl.ReportServiceImpl;
import com.ghtk.onlinebiddingproject.utils.converters.DtoToEntityConverter;
import com.ghtk.onlinebiddingproject.utils.converters.EntityToDtoConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "api/v1/admin/reports")
public class AdminReportController {
    @Autowired
    private ReportServiceImpl reportService;
    @Autowired
    private DtoToEntityConverter dtoToEntityConverter;
    @Autowired
    private EntityToDtoConverter entityToDtoConverter;

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CommonResponse> adminGetReports() {

        List<ReportDto> dtoResponse = entityToDtoConverter.convertToListReportDto(reportService.adminGetReports());
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}/results")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CommonResponse> adminJudgeReport(@PathVariable("id") int id, @Validated @RequestBody ReportResultDto reportResultDto) {
        Report report = reportService.adminGetById(id);
        ReportResult reportResult = dtoToEntityConverter.convertToEntity(reportResultDto);

        ReportResultDto dtoResponse = entityToDtoConverter.convertToDto(reportService.adminJudgeReport(reportResult, report));
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
