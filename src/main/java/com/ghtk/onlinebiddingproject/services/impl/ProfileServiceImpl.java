package com.ghtk.onlinebiddingproject.services.impl;

import com.ghtk.onlinebiddingproject.exceptions.NotFoundException;
import com.ghtk.onlinebiddingproject.models.dtos.ProfileDto;
import com.ghtk.onlinebiddingproject.models.entities.Profile;
import com.ghtk.onlinebiddingproject.models.requests.UserChangeProfile;
import com.ghtk.onlinebiddingproject.repositories.ProfileRepository;
import com.ghtk.onlinebiddingproject.security.UserDetailsImpl;
import com.ghtk.onlinebiddingproject.services.ProfileService;
import com.ghtk.onlinebiddingproject.utils.CurrentUserUtils;
import com.ghtk.onlinebiddingproject.utils.DtoToEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Profile getMyProfile() {
        UserDetailsImpl userDetails = CurrentUserUtils.getCurrentUserDetails();
        return profileRepository.findById(userDetails.getId())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy profile với id này!"));
    }

    @Override
    public Profile getById(Integer id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy profile với id này!"));
    }

    @Override
    public Profile putMyProfile(UserChangeProfile userChangeProfile) {
        UserDetailsImpl userDetails = CurrentUserUtils.getCurrentUserDetails();
        Profile profile = getById(userDetails.getId());
        DtoToEntityUtils.copyNonNullProperties(userChangeProfile, profile);
        return profileRepository.save(profile);
    }

    /**
     * For admin
     */
    @Override
    public Profile adminChangeUserStatus(Integer id, ProfileDto profileDto) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy profile với id này!"));
        profile.setStatus(profileDto.getStatus());
        return profileRepository.save(profile);
    }
}

