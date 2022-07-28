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
@Table(name = "notification")
public class Notification extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = true)
    private Profile profile;

    @Column(name = "notification_type")
    private String notificationType;

    @Column(name = "entity_type")
    private String entityType;

    @OneToMany(mappedBy = "notification", fetch = FetchType.LAZY)
    private List<NotificationNotified> notificationNotifieds;

    @OneToOne(mappedBy = "notification")
    private NotificationAuction notificationAuction;

    @OneToOne(mappedBy = "notification")
    private NotificationReport notificationReport;

    public Notification(Profile profile, String notificationType, String entityType) {
        this.profile = profile;
        this.notificationType = notificationType;
        this.entityType = entityType;
    }
}
