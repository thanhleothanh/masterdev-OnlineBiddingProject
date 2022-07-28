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
public class Notification extends BaseEntity{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "notification_type_name")
    private String notificationTypeName;

    @ManyToOne
    private NotificationNotified notificationNotified;
    @ManyToOne
    private NotificationNotifier notificationNotifier;

    public Notification(String notificationTypeName,NotificationNotified notificationNotified,NotificationNotifier notificationNotifier){
        this.notificationTypeName = notificationTypeName;
        this.notificationNotified = notificationNotified;
        this.notificationNotifier = notificationNotifier;
    }

}
