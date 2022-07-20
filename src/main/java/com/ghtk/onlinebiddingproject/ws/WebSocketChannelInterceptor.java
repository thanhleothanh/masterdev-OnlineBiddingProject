package com.ghtk.onlinebiddingproject.ws;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WebSocketChannelInterceptor implements ChannelInterceptor {
    static final String JWT_TOKEN_HEADER = "jwtToken";
    @Autowired
    private WebSocketUtils webSocketUtils;

    @SneakyThrows
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        final StompHeaderAccessor accessor = readHeaderAccessor(message);
        if (accessor.getCommand() == StompCommand.CONNECT) {
            String jwtToken = readJwtTokenHeader(accessor);
            UsernamePasswordAuthenticationToken userDetails = webSocketUtils.authenticate(jwtToken);
            if (userDetails == null)
                throw new AccessDeniedException("JWT Token not valid");
            else {
                accessor.setUser(userDetails);
                log.info("User with jwtToken '{}' make a WebSocket connection ", jwtToken);
            }
        }
        return message;
    }

    private StompHeaderAccessor readHeaderAccessor(Message<?> message) {
        final StompHeaderAccessor accessor = getAccessor(message);
        if (accessor == null) {
            throw new AuthenticationCredentialsNotFoundException("Fail to read headers.");
        }
        return accessor;
    }

    private String readJwtTokenHeader(StompHeaderAccessor accessor) {
        final String jwtToken = accessor.getFirstNativeHeader(JWT_TOKEN_HEADER);
        if (jwtToken == null || jwtToken.trim().isEmpty())
            throw new AuthenticationCredentialsNotFoundException("JWT Token not found!");
        return jwtToken;
    }

    StompHeaderAccessor getAccessor(Message<?> message) {
        return MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
    }
}
