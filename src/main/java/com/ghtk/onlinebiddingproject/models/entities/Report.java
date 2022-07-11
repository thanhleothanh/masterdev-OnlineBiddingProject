package com.ghtk.onlinebiddingproject.models.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "report")
public class Report extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_reporter_id", referencedColumnName = "profile_id", nullable = false)
    private User userReporter;

    @ManyToOne
    @JoinColumn(name = "user_reported_id", referencedColumnName = "profile_id", nullable = false)
    private User userReported;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id", nullable = true)
    private Auction auction;

    @OneToMany(mappedBy = "report")
    private List<ReportImage> reportImages;

    @OneToOne(mappedBy = "report")
    private ReportResult reportResult;


}
