package com.ghtk.onlinebiddingproject.models.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "verification_token")
public class VerificationToken {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "expiration_time", nullable = false)
    private LocalDateTime expirationTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @PrePersist
    public void prePersist() {
        this.expirationTime = LocalDateTime.now().plusMinutes(10L).truncatedTo(ChronoUnit.SECONDS);
    }

    public VerificationToken(Profile profile, String token) {
        this.token = token;
        this.profile = profile;
    }

}
