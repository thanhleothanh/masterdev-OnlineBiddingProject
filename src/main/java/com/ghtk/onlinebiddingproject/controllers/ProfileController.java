package com.ghtk.onlinebiddingproject.controllers;


import com.ghtk.onlinebiddingproject.models.dtos.AuctionDto;
import com.ghtk.onlinebiddingproject.models.dtos.ProfileDto;
import com.ghtk.onlinebiddingproject.models.requests.UserChangePassword;
import com.ghtk.onlinebiddingproject.models.requests.UserChangeProfile;
import com.ghtk.onlinebiddingproject.models.responses.CommonResponse;
import com.ghtk.onlinebiddingproject.services.impl.AuctionServiceImpl;
import com.ghtk.onlinebiddingproject.services.impl.AuthServiceImpl;
import com.ghtk.onlinebiddingproject.services.impl.ProfileServiceImpl;
import com.ghtk.onlinebiddingproject.utils.converters.DtoToEntityConverter;
import com.ghtk.onlinebiddingproject.utils.converters.EntityToDtoConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "api/v1/profiles")
public class ProfileController {
    @Autowired
    private AuthServiceImpl authService;
    @Autowired
    private ProfileServiceImpl profileService;
    @Autowired
    private AuctionServiceImpl auctionService;
    @Autowired
    private DtoToEntityConverter dtoToEntityConverter;
    @Autowired
    private EntityToDtoConverter entityToDtoConverter;


    @GetMapping("/myProfile")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<CommonResponse> getMyProfile() {
        ProfileDto dtoResponse = entityToDtoConverter.convertToDto(profileService.getMyProfile());
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/myProfile/changePassword")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<CommonResponse> changeMyPassword(@Validated @RequestBody UserChangePassword userChangePassword) {
        authService.changeMyPassword(userChangePassword);
        CommonResponse response = new CommonResponse(true, "Thay đổi mật khẩu thành công!", null, null);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PutMapping("/myProfile")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<CommonResponse> putMyProfile(@Validated @RequestBody UserChangeProfile userChangeProfile) {
        ProfileDto dtoResponse = entityToDtoConverter.convertToDto(profileService.putMyProfile(userChangeProfile));
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<CommonResponse> getById(@PathVariable(value = "id") Integer id) {
        ProfileDto dtoResponse = entityToDtoConverter.convertToDto(profileService.getById(id));
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}/auctions")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<CommonResponse> getAuctionsByUserId(@PathVariable(value = "id") Integer id) {
        profileService.getById(id);
        List<AuctionDto> dtoResponse = entityToDtoConverter.convertToListAuctionDto(auctionService.getAuctionsByUserId(id));
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
