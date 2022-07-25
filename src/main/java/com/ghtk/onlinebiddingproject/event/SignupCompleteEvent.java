package com.ghtk.onlinebiddingproject.event;

import com.ghtk.onlinebiddingproject.models.entities.Profile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class SignupCompleteEvent extends ApplicationEvent {

    private Profile profile;
    private String applicationEventURL;

    public SignupCompleteEvent(Profile profile, String applicationEventURL) {
        super(profile);
        this.profile = profile;
        this.applicationEventURL = applicationEventURL;
    }
}
