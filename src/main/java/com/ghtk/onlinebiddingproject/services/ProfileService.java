package com.ghtk.onlinebiddingproject.services;

import com.ghtk.onlinebiddingproject.models.dtos.ProfileDto;
import com.ghtk.onlinebiddingproject.models.entities.Profile;
import com.ghtk.onlinebiddingproject.models.requests.UserChangeProfile;

public interface ProfileService {
    Profile getMyProfile();

    Profile getById(Integer id);

    Profile putMyProfile(UserChangeProfile userChangeProfile);

    Profile adminChangeUserStatus(Integer id, ProfileDto profileDto);

}
