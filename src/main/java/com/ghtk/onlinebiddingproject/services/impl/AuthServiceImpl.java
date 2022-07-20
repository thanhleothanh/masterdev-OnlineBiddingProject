package com.ghtk.onlinebiddingproject.services.impl;

import com.ghtk.onlinebiddingproject.exceptions.BadRequestException;
import com.ghtk.onlinebiddingproject.models.entities.Profile;
import com.ghtk.onlinebiddingproject.models.entities.Role;
import com.ghtk.onlinebiddingproject.models.requests.UserChangePassword;
import com.ghtk.onlinebiddingproject.models.requests.UserLogin;
import com.ghtk.onlinebiddingproject.models.requests.UserSignup;
import com.ghtk.onlinebiddingproject.models.responses.UserAuthResponse;
import com.ghtk.onlinebiddingproject.repositories.ProfileRepository;
import com.ghtk.onlinebiddingproject.repositories.RoleRepository;
import com.ghtk.onlinebiddingproject.security.UserDetailsImpl;
import com.ghtk.onlinebiddingproject.services.AuthService;
import com.ghtk.onlinebiddingproject.utils.CurrentUserUtils;
import com.ghtk.onlinebiddingproject.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public UserAuthResponse login(UserLogin loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        CurrentUserUtils.setCurrentUserDetails(authentication);
        UserDetailsImpl userDetails = CurrentUserUtils.getCurrentUserDetails();

        return new UserAuthResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getName(), userDetails.getEmail(), userDetails.getImageUrl(), userDetails.getAuthorities().stream().findFirst().get().getAuthority(), userDetails.getStatus());
    }

    @Override
    public UserAuthResponse signUp(UserSignup signupRequest) {
        if (profileRepository.existsByUsername(signupRequest.getUsername())) {
            throw new BadRequestException("Username đã được sử dụng!");
        }
        if (signupRequest.getRole().getId().equals(1)) {
            throw new AccessDeniedException("Không được phép tạo tài khoản admin!");
        }
        Role userRole = roleRepository.findById(signupRequest.getRole().getId()).get();
        Profile newUser = profileRepository.save(new Profile(signupRequest.getUsername(),
                encoder.encode(signupRequest.getPassword()), signupRequest.getName(), userRole));

        if (userRole.getId() == 1) profileRepository.insertAdmin(newUser.getId());
        else profileRepository.insertUser(newUser.getId());

        return new UserAuthResponse(newUser.getId(), newUser.getUsername(), newUser.getName(), newUser.getEmail(), newUser.getImageUrl(), newUser.getRole().getName(), newUser.getStatus());
    }

    @Override
    public void changeMyPassword(UserChangePassword userChangePassword) {
        UserDetailsImpl userDetails = CurrentUserUtils.getCurrentUserDetails();
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userChangePassword.getCurrentPassword()));
        if (!userChangePassword.getNewPassword().equals(userChangePassword.getNewPasswordConfirm())) {
            throw new BadRequestException("Xác nhận lại mật khẩu mới!");
        }
        Profile profile = profileRepository.findById(userDetails.getId()).get();
        profile.setPassword(encoder.encode(userChangePassword.getNewPassword()));
        profileRepository.save(profile);
    }

    @Override
    public ResponseCookie generateJwtCookie() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return jwtUtils.generateJwtCookie(userDetails);
    }

    @Override
    public ResponseCookie cleanJwtCookie() {
        return jwtUtils.getCleanJwtCookie();
    }
}
