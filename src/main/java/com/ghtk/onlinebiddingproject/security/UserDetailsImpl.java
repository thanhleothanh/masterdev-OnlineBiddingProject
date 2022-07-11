package com.ghtk.onlinebiddingproject.security;

import com.ghtk.onlinebiddingproject.constants.UserStatusConstants;
import com.ghtk.onlinebiddingproject.models.entities.Profile;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String imageUrl;
    private UserStatusConstants status;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Integer id, String username, String password, String name, String email, String imageUrl, UserStatusConstants status,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.status = status;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(Profile profile) {
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(profile.getRole().getName()));

        return new UserDetailsImpl(
                profile.getId(),
                profile.getUsername(),
                profile.getPassword(),
                profile.getName(),
                profile.getEmail(),
                profile.getImageUrl(),
                profile.getStatus(),
                authorities);

    }

    public boolean isSuspended() {
        return this.status.equals(UserStatusConstants.SUSPENDED);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.status.equals(UserStatusConstants.BANNED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
