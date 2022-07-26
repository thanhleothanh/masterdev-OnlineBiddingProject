package com.ghtk.onlinebiddingproject.repositories;

import com.ghtk.onlinebiddingproject.constants.ReportResultConstants;
import com.ghtk.onlinebiddingproject.models.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReportRepository extends JpaRepository<Report, Integer>, JpaSpecificationExecutor<Report> {
    long countByUserReported_IdAndReportResult_Result(Integer id, ReportResultConstants result);

}
