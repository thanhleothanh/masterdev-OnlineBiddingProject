package com.ghtk.onlinebiddingproject.services;

import com.ghtk.onlinebiddingproject.models.entities.Profile;
import com.ghtk.onlinebiddingproject.models.entities.VerificationToken;
import com.ghtk.onlinebiddingproject.models.requests.UserChangePassword;
import com.ghtk.onlinebiddingproject.models.requests.UserLogin;
import com.ghtk.onlinebiddingproject.models.requests.UserSignup;
import com.ghtk.onlinebiddingproject.models.responses.UserAuthResponse;
import org.springframework.http.ResponseCookie;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {

    UserAuthResponse login(UserLogin loginRequest, HttpServletRequest request);

    UserAuthResponse signUp(UserSignup signupRequest, final HttpServletRequest request);

    void changeMyPassword(UserChangePassword userChangePassword);

    ResponseCookie generateJwtCookie();

    ResponseCookie cleanJwtCookie();

    void saveVerificationTokenForProfile(String token, Profile profile);

    String validateVerificationToken(String token);

    VerificationToken garenateNewVerification(VerificationToken verificationToken);
}
