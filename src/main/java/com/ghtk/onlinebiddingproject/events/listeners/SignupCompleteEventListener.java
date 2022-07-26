package com.ghtk.onlinebiddingproject.events.listeners;

import com.ghtk.onlinebiddingproject.events.SignupCompleteEvent;
import com.ghtk.onlinebiddingproject.models.entities.Profile;
import com.ghtk.onlinebiddingproject.services.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SignupCompleteEventListener implements ApplicationListener<SignupCompleteEvent> {

    @Autowired
    private AuthServiceImpl authService;

    @Override
    public void onApplicationEvent(SignupCompleteEvent event) {
        // Create the verification Token for  ther profile with link
        Profile profile = event.getProfile();
        String token = UUID.randomUUID().toString();
        authService.saveVerificationTokenForProfile(token, profile);
        // Send Mail to user
        String url = event.getApplicationEventURL()
                + "verificationSignup?token="
                + token;
        authService.sendMailVerification(profile, url);
    }
}
