package com.ghtk.onlinebiddingproject.services.impl;

import com.ghtk.onlinebiddingproject.exceptions.NotFoundException;
import com.ghtk.onlinebiddingproject.models.dtos.ProfileDto;
import com.ghtk.onlinebiddingproject.models.entities.Profile;
import com.ghtk.onlinebiddingproject.models.requests.UserChangeProfile;
import com.ghtk.onlinebiddingproject.models.responses.ProfilePagingResponse;
import com.ghtk.onlinebiddingproject.repositories.ProfileRepository;
import com.ghtk.onlinebiddingproject.security.UserDetailsImpl;
import com.ghtk.onlinebiddingproject.services.ProfileService;
import com.ghtk.onlinebiddingproject.utils.CurrentUserUtils;
import com.ghtk.onlinebiddingproject.utils.DtoToEntityUtils;
import com.ghtk.onlinebiddingproject.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public ProfilePagingResponse adminGetAll(Specification<Profile> spec, HttpHeaders headers, Sort sort) {
        if (PaginationUtils.isPaginationRequested(headers)) {
            return helperGet(spec, PaginationUtils.buildPageRequest(headers, sort));
        } else {
            List<Profile> profileEntities = helperGet(spec, sort);
            return new ProfilePagingResponse(profileEntities.size(), 0, 0, 0, profileEntities);
        }
    }

    /**
     * helper methods
     */

    public List<Profile> helperGet(Specification<Profile> spec, Sort sort) {
        return profileRepository.findAll(spec, sort);
    }

    public ProfilePagingResponse helperGet(Specification<Profile> spec, Pageable pageable) {
        Page<Profile> page = profileRepository.findAll(spec, pageable);
        List<Profile> profileEntities = page.getContent();
        return new ProfilePagingResponse((int) page.getTotalElements(), page.getNumber(), page.getNumberOfElements(), page.getTotalPages(), profileEntities);
    }
}

