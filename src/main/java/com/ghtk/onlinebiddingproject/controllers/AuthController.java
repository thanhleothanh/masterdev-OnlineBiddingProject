package com.ghtk.onlinebiddingproject.controllers;

import com.ghtk.onlinebiddingproject.models.requests.UserLogin;
import com.ghtk.onlinebiddingproject.models.requests.UserSignup;
import com.ghtk.onlinebiddingproject.models.responses.CommonResponse;
import com.ghtk.onlinebiddingproject.models.responses.UserAuthResponse;
import com.ghtk.onlinebiddingproject.services.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthServiceImpl authService;

    @PostMapping("/login")
    public ResponseEntity<CommonResponse> loginUser(@Valid @RequestBody UserLogin loginRequest) {
        UserAuthResponse userLoginResponse = authService.login(loginRequest);
        ResponseCookie jwtCookie = authService.generateJwtCookie();

        CommonResponse commonResponse = new CommonResponse(true, "Đăng nhập thành công!", userLoginResponse, null);
        return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(commonResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<CommonResponse> signUpUser(@Valid @RequestBody UserSignup signUpRequest) {
        UserAuthResponse userRegisterResponse = authService.signUp(signUpRequest);

        CommonResponse commonResponse = new CommonResponse(true, "Đăng ký thành công!", userRegisterResponse, null);
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<CommonResponse> logoutUser() {
        ResponseCookie jwtCookie = authService.cleanJwtCookie();

        CommonResponse commonResponse = new CommonResponse(true, "Đăng xuất thành công!", null, null);
        return ResponseEntity.status(HttpStatus.ACCEPTED).header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(commonResponse);
    }
}
