package com.ghtk.onlinebiddingproject.repositories;

import com.ghtk.onlinebiddingproject.models.entities.ReportResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportResultRepository extends JpaRepository<ReportResult, Integer> {
    ReportResult findByReport_Id(Integer id);

}
