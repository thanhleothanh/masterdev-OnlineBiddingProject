package com.ghtk.onlinebiddingproject.controllers;

import com.ghtk.onlinebiddingproject.models.dtos.AuctionDto;
import com.ghtk.onlinebiddingproject.models.dtos.ProfileDto;
import com.ghtk.onlinebiddingproject.models.dtos.UserDto;
import com.ghtk.onlinebiddingproject.models.responses.CommonResponse;
import com.ghtk.onlinebiddingproject.services.impl.UserServiceImpl;
import com.ghtk.onlinebiddingproject.utils.converters.EntityToDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private EntityToDtoConverter entityToDtoConverter;

    @GetMapping("profileuser")
    public ResponseEntity<CommonResponse> getByProfile(){

        ProfileDto dtoResponse = entityToDtoConverter.convertDto(userService.getByProfile());
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
