package com.ghtk.onlinebiddingproject.models.entities;


import com.ghtk.onlinebiddingproject.constants.ReviewResultConstants;
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
@Table(name = "review_result")
public class ReviewResult extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "result", nullable = false)
    private ReviewResultConstants result;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id", nullable = false)
    private Auction auction;

    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "profile_id", nullable = false)
    private Admin admin;
}
