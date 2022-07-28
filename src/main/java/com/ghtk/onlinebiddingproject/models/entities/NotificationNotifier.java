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
@Table(name = "notification_notifier")
public class NotificationNotifier {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "notificationNotifier")
    private List<Notification> notification;

    @OneToOne
    @JoinColumn(name = "notifier_id",referencedColumnName = "id", nullable = false)
    private Profile notifier;

    public NotificationNotifier(Profile notifier){
        this.notifier = notifier;
    }
}
