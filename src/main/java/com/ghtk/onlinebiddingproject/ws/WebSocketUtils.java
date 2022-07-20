package com.ghtk.onlinebiddingproject.ws;

import com.ghtk.onlinebiddingproject.security.UserDetailsImpl;
import com.ghtk.onlinebiddingproject.security.UserDetailsServiceImpl;
import com.ghtk.onlinebiddingproject.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class WebSocketUtils {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public UsernamePasswordAuthenticationToken authenticate(String jwt) {
        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
            String username = jwtUtils.getUserNameFromJwtToken(jwt);
            UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);

            return new UsernamePasswordAuthenticationToken(userDetails,
                    null,
                    userDetails.getAuthorities());
        } else return null;
    }
}
