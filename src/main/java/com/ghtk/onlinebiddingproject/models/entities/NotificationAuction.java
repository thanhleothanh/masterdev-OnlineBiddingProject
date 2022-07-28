package com.ghtk.onlinebiddingproject.models.entities;

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
@Table(name = "notification_auction")
public class NotificationAuction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "notification_id", referencedColumnName = "id",nullable = false)
    private Notification notification;

    @OneToOne
    @JoinColumn(name = "auction_id", referencedColumnName = "user_id",nullable = false)
    private Auction auction;
}
