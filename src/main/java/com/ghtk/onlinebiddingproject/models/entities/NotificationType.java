package com.ghtk.onlinebiddingproject.models.entities;

import com.ghtk.onlinebiddingproject.constants.EntityTypeConstants;
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
@Table(name = "notification_type")
public class NotificationType {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "notification_type_name")
    private String notificationTypeName;

    @Column(name = "entity_type")
    private EntityTypeConstants entityType;

}
