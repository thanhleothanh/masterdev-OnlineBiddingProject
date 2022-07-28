package com.ghtk.onlinebiddingproject.services.impl;

import com.ghtk.onlinebiddingproject.exceptions.BadRequestException;
import com.ghtk.onlinebiddingproject.exceptions.NotFoundException;
import com.ghtk.onlinebiddingproject.models.entities.Admin;
import com.ghtk.onlinebiddingproject.models.entities.Report;
import com.ghtk.onlinebiddingproject.models.entities.ReportImage;
import com.ghtk.onlinebiddingproject.models.entities.ReportResult;
import com.ghtk.onlinebiddingproject.models.responses.ReportPagingResponse;
import com.ghtk.onlinebiddingproject.repositories.ReportImageRepository;
import com.ghtk.onlinebiddingproject.repositories.ReportRepository;
import com.ghtk.onlinebiddingproject.repositories.ReportResultRepository;
import com.ghtk.onlinebiddingproject.security.UserDetailsImpl;
import com.ghtk.onlinebiddingproject.services.ReportService;
import com.ghtk.onlinebiddingproject.utils.CurrentUserUtils;
import com.ghtk.onlinebiddingproject.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
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
        UserDetailsImpl userDetails = CurrentUserUtils.getCurrentUserDetails();
        if (userDetails.isSuspended()) throw new AccessDeniedException("Tài khoản của bạn đang bị giới hạn!");

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
    public ReportPagingResponse get(Specification<Report> spec, HttpHeaders headers, Sort sort) {
        if (PaginationUtils.isPaginationRequested(headers)) {
            return helperGet(spec, PaginationUtils.buildPageRequest(headers, sort));
        } else {
            List<Report> reportEntities = helperGet(spec, sort);
            return new ReportPagingResponse(reportEntities.size(), 0, 0, 0, reportEntities);
        }
    }

    @Override
    public Report adminGetById(Integer id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy phiếu báo cáo với id này!"));
    }

    @Override
    public void adminDeleteById(Integer id) {
        reportRepository.deleteById(id);
    }

    @Override
    public List<Report> adminGetReports() {
        return reportRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public ReportResult adminJudgeReport(ReportResult reportResult, Report report) {
        ReportResult existingResult = reportResultRepository.findByReport_Id(report.getId());
        if (existingResult == null) {
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

    /**
     * helper methods
     */

    public List<Report> helperGet(Specification<Report> spec, Sort sort) {
        return reportRepository.findAll(spec, sort);
    }

    public ReportPagingResponse helperGet(Specification<Report> spec, Pageable pageable) {
        Page<Report> page = reportRepository.findAll(spec, pageable);
        List<Report> reportEntities = page.getContent();
        return new ReportPagingResponse((int) page.getTotalElements(), page.getNumber(), page.getNumberOfElements(), page.getTotalPages(), reportEntities);
    }
}
