package com.ghtk.onlinebiddingproject.controllers;


import com.ghtk.onlinebiddingproject.constants.AuctionStatusConstants;
import com.ghtk.onlinebiddingproject.models.dtos.AuctionDto;
import com.ghtk.onlinebiddingproject.models.dtos.AuctionUserDto;
import com.ghtk.onlinebiddingproject.models.dtos.ProfileDto;
import com.ghtk.onlinebiddingproject.models.entities.Auction;
import com.ghtk.onlinebiddingproject.models.requests.UserChangePassword;
import com.ghtk.onlinebiddingproject.models.requests.UserChangeProfile;
import com.ghtk.onlinebiddingproject.models.responses.CommonResponse;
import com.ghtk.onlinebiddingproject.services.impl.AuctionServiceImpl;
import com.ghtk.onlinebiddingproject.services.impl.AuctionUserServiceImpl;
import com.ghtk.onlinebiddingproject.services.impl.AuthServiceImpl;
import com.ghtk.onlinebiddingproject.services.impl.ProfileServiceImpl;
import com.ghtk.onlinebiddingproject.utils.converters.EntityToDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/profiles")
public class ProfileController {
    @Autowired
    private AuthServiceImpl authService;
    @Autowired
    private ProfileServiceImpl profileService;
    @Autowired
    private AuctionServiceImpl auctionService;
    @Autowired
    private AuctionUserServiceImpl auctionUserService;
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
    public ResponseEntity<CommonResponse> changeMyPassword(@Valid @RequestBody UserChangePassword userChangePassword) {
        authService.changeMyPassword(userChangePassword);
        CommonResponse response = new CommonResponse(true, "Thay đổi mật khẩu thành công!", null, null);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PutMapping("/myProfile")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<CommonResponse> putMyProfile(@Valid @RequestBody UserChangeProfile userChangeProfile) {
        ProfileDto dtoResponse = entityToDtoConverter.convertToDto(profileService.putMyProfile(userChangeProfile));
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/myAuctions")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CommonResponse> getMyAuctions(@RequestParam(name = "status", required = false) AuctionStatusConstants status) {
        List<Auction> auctions = auctionService.getMyAuctions(status);
        List<AuctionDto> dtoResponse = entityToDtoConverter.convertToListAuctionDto(auctions);
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
     * Interested Auctions
     * */

    @GetMapping("/myInterestedAuctions")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<CommonResponse> getMyInterestedAuctions() {
        List<AuctionDto> dtoResponse = entityToDtoConverter.convertToListAuctionDto(auctionService.getMyInterestedAuctions());
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("myInterestedAuctions/{auctionId}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CommonResponse> getInterestedAuction(@PathVariable(value = "auctionId") Integer auctionId) {
        boolean interested = auctionUserService.getInterestedAuction(auctionId);
        CommonResponse response = new CommonResponse(true, "Người dùng đã quan tâm auction này trước đó!", null, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/myInterestedAuctions")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CommonResponse> saveInterestedAuction(@Valid @RequestBody AuctionUserDto auctionUserDto) {
        auctionUserService.saveInterestedAuction(auctionUserDto.getAuction().getId());
        CommonResponse response = new CommonResponse(true, "Success", null, null);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("myInterestedAuctions/{auctionId}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CommonResponse> removeInterestedAuction(@PathVariable(value = "auctionId") Integer auctionId) {
        auctionUserService.removeInterestedAuction(auctionId);
        CommonResponse response = new CommonResponse(true, "Success", null, null);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    /*
     * By User Id
     * */

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

    @GetMapping("/{id}/wonAuctions")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<CommonResponse> getWonAuctionsByUserId(@PathVariable(value = "id") Integer id) {
        profileService.getById(id);
        List<AuctionDto> dtoResponse = entityToDtoConverter.convertToListAuctionDto(auctionService.getWonAuctionsByUserId(id));
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
