package com.ghtk.onlinebiddingproject.models.entities;


import com.ghtk.onlinebiddingproject.constants.ReportResultConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "report_result")
public class ReportResult extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "result", nullable = false)
    private ReportResultConstants result;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;

    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "profile_id", nullable = false)
    private Admin admin;
}
