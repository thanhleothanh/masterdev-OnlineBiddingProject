package com.ghtk.onlinebiddingproject.models.entities;

import lombok.*;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "notification")
public class Notification {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "notification_type_id", referencedColumnName = "id", nullable = false)
    private NotificationType notificationType;

    @Column(name = "create_on")
    private LocalDateTime createOn;

    @ManyToOne
    private NotificationNotified notificationNotified;
    @ManyToOne
    private NotificationNotifier notificationNotifier;

}
