package com.codehunter.countdowntimernative.util;

import com.codehunter.countdowntimernative.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public class AuthenticationUtil {
    private AuthenticationUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Jwt principal = (Jwt) auth.getPrincipal();
        String userId = principal.getClaimAsString("sub");
        String username = principal.getClaimAsString("preferred_username");
        return User.builder().id(userId).name(username).build();
    }
}
