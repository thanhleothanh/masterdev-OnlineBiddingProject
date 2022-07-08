package com.ghtk.onlinebiddingproject.services.impl;

import com.ghtk.onlinebiddingproject.constants.UserStatusConstants;
import com.ghtk.onlinebiddingproject.exceptions.NotFoundException;
import com.ghtk.onlinebiddingproject.models.entities.Profile;
import com.ghtk.onlinebiddingproject.models.entities.User;
import com.ghtk.onlinebiddingproject.repositories.ProfileRepository;
import com.ghtk.onlinebiddingproject.repositories.UserRepository;
import com.ghtk.onlinebiddingproject.security.UserDetailsImpl;
import com.ghtk.onlinebiddingproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy user với id này!"));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User put(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy user với id này!"));
//        userRepository.delete(user);
        user.getProfile().setStatus(UserStatusConstants.BANNED);
        userRepository.save(user);
    }

    @Override
    public Profile getByProfile() {
        UserDetailsImpl
                userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return profileRepository.findById(userDetails.getId()).orElseThrow(()-> new NotFoundException("khong tim thay id!!!"));
    }


}
