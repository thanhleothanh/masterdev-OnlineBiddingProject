package com.ghtk.onlinebiddingproject.controllers;

import com.ghtk.onlinebiddingproject.models.requests.UserLogin;
import com.ghtk.onlinebiddingproject.models.requests.UserSignup;
import com.ghtk.onlinebiddingproject.models.responses.CommonResponse;
import com.ghtk.onlinebiddingproject.models.responses.UserAuthResponse;
import com.ghtk.onlinebiddingproject.repositories.ProfileRepository;
import com.ghtk.onlinebiddingproject.services.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthServiceImpl authService;
    @Autowired
    private ProfileRepository profileRepository;

    @PostMapping("/login")
    public ResponseEntity<CommonResponse> loginUser(@Valid @RequestBody UserLogin loginRequest, HttpServletRequest request) {
        UserAuthResponse userLoginResponse = authService.login(loginRequest, request);
        ResponseCookie jwtCookie = authService.generateJwtCookie();
        CommonResponse commonResponse = new CommonResponse(true, "Đăng nhập thành công!", userLoginResponse, null);
        return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(commonResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<CommonResponse> signUpUser(@Validated @RequestBody UserSignup signUpRequest, final HttpServletRequest request) {
        UserAuthResponse userRegisterResponse = authService.signUp(signUpRequest, request);

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

    @GetMapping("/verificationSignup")
    public ResponseEntity<CommonResponse> verificationSignup(@RequestParam("token") String token) {
        String result = authService.validateVerificationToken(token);
        if (result.equalsIgnoreCase("valid")) {
            CommonResponse commonResponse = new CommonResponse(true, "Success", "xác thực email thành công", null);
            return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
        }
        CommonResponse commonResponse = new CommonResponse(false, "Bad User", "xác thực email thất bại", null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(commonResponse);
    }
}
