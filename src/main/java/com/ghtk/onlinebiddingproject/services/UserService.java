package com.ghtk.onlinebiddingproject.services;

import com.ghtk.onlinebiddingproject.models.entities.Profile;
import com.ghtk.onlinebiddingproject.models.entities.User;

public interface UserService {

    User getById(Integer id);

    User save(User user);

    User put(User user);

    void deleteById(Integer id);

    Profile getByProfile();
}
