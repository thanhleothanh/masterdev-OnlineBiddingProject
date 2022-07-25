package com.ghtk.onlinebiddingproject.models.entities;

import com.ghtk.onlinebiddingproject.constants.UserStatusConstants;
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
@Table(name = "profile")
public class Profile extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "image_url", nullable = true)
    private String imageUrl;

    @Column(name = "status", nullable = false)
    private UserStatusConstants status;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;


    @PrePersist
    public void prePersist() {
        this.status = UserStatusConstants.valueOf("INACTIVE");
    }

    public Profile(String username, String password, String name, String email , Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
    }
}
