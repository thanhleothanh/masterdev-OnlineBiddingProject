package com.ghtk.onlinebiddingproject.services;

import com.ghtk.onlinebiddingproject.models.entities.Report;
import com.ghtk.onlinebiddingproject.models.entities.ReportImage;
import com.ghtk.onlinebiddingproject.models.entities.ReportResult;

import java.util.List;

public interface ReportService {
    Report save(Report report);

    Report adminGetById(Integer id);

    List<Report> adminGetReports();

    ReportResult adminJudgeReport(ReportResult reportResult, Report report);

    ReportImage saveReportImage(Integer id, ReportImage reportImage);
}
