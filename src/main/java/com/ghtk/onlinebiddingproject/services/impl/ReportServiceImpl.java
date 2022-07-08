package com.ghtk.onlinebiddingproject.services.impl;

import com.ghtk.onlinebiddingproject.exceptions.BadRequestException;
import com.ghtk.onlinebiddingproject.exceptions.NotFoundException;
import com.ghtk.onlinebiddingproject.models.entities.*;
import com.ghtk.onlinebiddingproject.repositories.ReportImageRepository;
import com.ghtk.onlinebiddingproject.repositories.ReportRepository;
import com.ghtk.onlinebiddingproject.repositories.ReportResultRepository;
import com.ghtk.onlinebiddingproject.security.UserDetailsImpl;
import com.ghtk.onlinebiddingproject.services.ReportService;
import com.ghtk.onlinebiddingproject.utils.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private ReportResultRepository reportResultRepository;
    @Autowired
    private ReportImageRepository reportImageRepository;

    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public Report save(Report report) {
        Report newReport = reportRepository.save(report);
        List<ReportImage> reportImages = report.getReportImages();
        if (reportImages != null && reportImages.size() != 0) {
            for (ReportImage reportImage : reportImages) {
                reportImage.setReport(newReport);
                reportImageRepository.save(reportImage);
            }
        }
        return newReport;
    }

    /*
     * For admin
     * */

    @Override
    public Report adminGetById(Integer id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy phiếu báo cáo với id này!"));
    }

    @Override
    public List<Report> adminGetReports() {
        return reportRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public ReportResult adminJudgeReport(ReportResult reportResult, Report report) {
        ReportResult existingResult = reportResultRepository.findByReport_Id(report.getId());
        if (existingResult != null) {
            UserDetailsImpl userDetails = CurrentUserUtils.getCurrentUserDetails();
            reportResult.setAdmin(new Admin(userDetails.getId()));
            reportResult.setReport(report);
            return reportResultRepository.save(reportResult);
        } else throw new BadRequestException("Phiếu báo cáo này đã được xem xét trước đó!");
    }

    /*
     * Report Images
     * */

    @Override
    public ReportImage saveReportImage(Integer id, ReportImage reportImage) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy report với id này!"));
        boolean isPostedByCurrentUser = CurrentUserUtils.isPostedByCurrentUser(report.getUserReporter().getId());

        if (isPostedByCurrentUser) {
            reportImage.setReport(report);
            return reportImageRepository.save(reportImage);
        } else throw new AccessDeniedException("Không có quyền thêm ảnh vào phiếu báo cáo này!");
    }
}
