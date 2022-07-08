package com.ghtk.onlinebiddingproject.controllers;

import com.ghtk.onlinebiddingproject.models.dtos.ProfileDto;
import com.ghtk.onlinebiddingproject.models.responses.CommonResponse;
import com.ghtk.onlinebiddingproject.services.impl.ProfileServiceImpl;
import com.ghtk.onlinebiddingproject.utils.converters.EntityToDtoConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(path = "api/v1/admin/profiles")
public class AdminProfileController {
    @Autowired
    private ProfileServiceImpl profileService;
    @Autowired
    private EntityToDtoConverter entityToDtoConverter;

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CommonResponse> adminChangeUserStatus(@PathVariable("id") int id, @RequestBody @Validated ProfileDto profileDto) {
        ProfileDto dtoResponse = entityToDtoConverter.convertToDto(profileService.adminChangeUserStatus(id, profileDto));
        CommonResponse response = new CommonResponse(true, "Success", dtoResponse, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
