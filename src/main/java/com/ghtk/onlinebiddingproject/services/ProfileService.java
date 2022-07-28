package com.ghtk.onlinebiddingproject.services;

import com.ghtk.onlinebiddingproject.models.dtos.ProfileDto;
import com.ghtk.onlinebiddingproject.models.entities.Profile;
import com.ghtk.onlinebiddingproject.models.requests.UserChangeProfile;
import com.ghtk.onlinebiddingproject.models.responses.ProfilePagingResponse;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;

public interface ProfileService {

    Profile getMyProfile();

    Profile getById(Integer id);

    Profile putMyProfile(UserChangeProfile userChangeProfile);

    Profile adminChangeUserStatus(Integer id, ProfileDto profileDto);

    ProfilePagingResponse adminGetAll(Specification<Profile> spec, HttpHeaders headers, Sort sort);
}
