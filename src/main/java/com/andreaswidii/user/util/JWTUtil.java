package com.andreaswidii.user.util;


import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Optional;

public final class JWTUtil {
    public static Optional<Long> getUserId() {
        return getJwt()
                .map(jwt -> jwt.getClaimAsString("userid"))
                .map(userIdStr -> {
                    try {
                        return Long.valueOf(userIdStr);
                    } catch (NumberFormatException e) {
                        return null;
                    }
                });
    }

    private static Optional<Jwt> getJwt() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .filter(JwtAuthenticationToken.class::isInstance)
                .map(JwtAuthenticationToken.class::cast)
                .map(JwtAuthenticationToken::getToken);
    }
}
