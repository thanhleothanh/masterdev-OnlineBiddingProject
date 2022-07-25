package com.ghtk.onlinebiddingproject.services.impl;

import com.ghtk.onlinebiddingproject.constants.MailConstants;
import com.ghtk.onlinebiddingproject.constants.UserStatusConstants;
import com.ghtk.onlinebiddingproject.event.SignupCompleteEvent;
import com.ghtk.onlinebiddingproject.exceptions.BadRequestException;
import com.ghtk.onlinebiddingproject.exceptions.NotFoundException;
import com.ghtk.onlinebiddingproject.models.dtos.DataMailDto;
import com.ghtk.onlinebiddingproject.models.entities.Profile;
import com.ghtk.onlinebiddingproject.models.entities.Role;
import com.ghtk.onlinebiddingproject.models.entities.VerificationToken;
import com.ghtk.onlinebiddingproject.models.requests.UserChangePassword;
import com.ghtk.onlinebiddingproject.models.requests.UserLogin;
import com.ghtk.onlinebiddingproject.models.requests.UserSignup;
import com.ghtk.onlinebiddingproject.models.responses.UserAuthResponse;
import com.ghtk.onlinebiddingproject.repositories.ProfileRepository;
import com.ghtk.onlinebiddingproject.repositories.RoleRepository;
import com.ghtk.onlinebiddingproject.repositories.VerificationTokenRepository;
import com.ghtk.onlinebiddingproject.security.UserDetailsImpl;
import com.ghtk.onlinebiddingproject.services.AuthService;
import com.ghtk.onlinebiddingproject.utils.CurrentUserUtils;
import com.ghtk.onlinebiddingproject.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseCookie;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private MailServiceImpl mailService;


    @Override
    public UserAuthResponse login(UserLogin loginRequest, HttpServletRequest request) {
        Profile profile = profileRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy user với username: " + loginRequest.getUsername()));

        VerificationToken verificationToken = verificationTokenRepository.findByProfile_Id(profile.getId());
        if (profile.getStatus() == UserStatusConstants.INACTIVE && verificationToken.getExpirationTime().isAfter(LocalDateTime.now())) {
            throw new AccessDeniedException("Tài khoản chưa được xác thực hãy vào mail ấn link để xác thực!");
        }

        if (profile.getStatus() == UserStatusConstants.INACTIVE && LocalDateTime.now().isAfter(verificationToken.getExpirationTime())) {
            VerificationToken newVerification = garenateNewVerification(verificationToken);
            resendVerificationMail(profile, applicationUrl(request), verificationToken);
            throw new AccessDeniedException("Tài khoản chưa được xác thực chúng tôi đã gửi cho bạn một link khác!");
        }

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        CurrentUserUtils.setCurrentUserDetails(authentication);
        UserDetailsImpl userDetails = CurrentUserUtils.getCurrentUserDetails();

        return new UserAuthResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getName(), userDetails.getEmail(), userDetails.getImageUrl(), userDetails.getAuthorities().stream().findFirst().get().getAuthority(), userDetails.getStatus());
    }

    @Override
    public UserAuthResponse signUp(UserSignup signupRequest, final HttpServletRequest request) {
        if (profileRepository.existsByUsername(signupRequest.getUsername()))
            throw new BadRequestException("Username đã được sử dụng!");
        if (profileRepository.existsByEmail(signupRequest.getEmail()))
            throw new BadRequestException("Email đã được sử dụng!");
        if (signupRequest.getRole().getId().equals(1))
            throw new AccessDeniedException("Không được phép tạo tài khoản admin!");

        Role userRole = roleRepository.findById(signupRequest.getRole().getId()).get();
        Profile newUser = profileRepository.save(new Profile(signupRequest.getUsername(),
                encoder.encode(signupRequest.getPassword()), signupRequest.getName(), signupRequest.getEmail(), userRole));

        if (userRole.getId() == 1) profileRepository.insertAdmin(newUser.getId());
        else {
            profileRepository.insertUser(newUser.getId());
            publisher.publishEvent(new SignupCompleteEvent(newUser, applicationUrl(request)));
        }
        return new UserAuthResponse(newUser.getId(), newUser.getUsername(), newUser.getName(), newUser.getEmail(), newUser.getImageUrl(), newUser.getRole().getName(), newUser.getStatus());
    }

    @Override
    public void changeMyPassword(UserChangePassword userChangePassword) {
        UserDetailsImpl userDetails = CurrentUserUtils.getCurrentUserDetails();
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userChangePassword.getCurrentPassword()));
        if (!userChangePassword.getNewPassword().equals(userChangePassword.getConfirmedNewPassword())) {
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


    @Override
    public void saveVerificationTokenForProfile(String token, Profile profile) {
        VerificationToken verificationToken = new VerificationToken(profile, token);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String validateVerificationToken(String token) {
        VerificationToken verificationToken
                = verificationTokenRepository.findByToken(token);
        if (verificationToken == null) {
            return "invalid";
        }
        Profile profile = profileRepository.findById(verificationToken.getProfile().getId())
                .orElseThrow(() -> new NotFoundException("không tìm thấy profile"));
        if (LocalDateTime.now().isAfter(verificationToken.getExpirationTime())) {
            return "expired";
        }
        profile.setStatus(UserStatusConstants.ACTIVE);
        profileRepository.save(profile);
        verificationTokenRepository.delete(verificationToken);
        return "valid";
    }

    @Override
    public VerificationToken garenateNewVerification(VerificationToken verificationToken) {
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationToken.setExpirationTime(LocalDateTime.now().plusMinutes(10L));
        verificationTokenRepository.save(verificationToken);
        return verificationToken;
    }


    public void resendVerificationMail(Profile profile, String applicationUrl, VerificationToken verificationToken) {
        String url = applicationUrl
                + "verificationSignup?token="
                + verificationToken.getToken();
        log.info("CLick the link to verify your account {}", url);
        sendMailVerification(profile, url);
    }

    public void sendMailVerification(Profile profile, String token) {
        try {
            DataMailDto dataMail = new DataMailDto();
            dataMail.setTo(profile.getEmail());
            dataMail.setSubject(MailConstants.CLIENT_REGISTER_SUBJECT.CLIENT_REGISTER);

            Map<String, Object> props = new HashMap<>();
            props.put("name", profile.getName());
            props.put("userName", profile.getUsername());
            props.put("email", profile.getEmail());
            props.put(("token"), token);
            dataMail.setProps(props);

            mailService.sendMail(dataMail, MailConstants.TEMPATE_FILE_NAME.CLIENT_REGISTER);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://"
                + request.getServerName()
                + ":" + request.getServerPort() + "/api/v1/auth/"
                + request.getContextPath();
    }
}
