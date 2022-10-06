package com.unosquare.sailingapp.security;

import static com.unosquare.sailingapp.constant.AppConstants.NOT_PERMITTED;

import com.unosquare.sailingapp.exception.UnauthorizedException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.annotation.RequestScope;

import java.util.ArrayList;

@Configuration
public class AppUserSecurity {

    @Bean
    @RequestScope
    public CurrentAppUser get() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            final CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            return CurrentAppUser.builder()
                    .id(customUserDetails.getId())
                    .username(customUserDetails.getUsername())
                    .userAccessStatus(getUserAccessStatus(customUserDetails))
                    .build();
        }
        throw new UnauthorizedException(NOT_PERMITTED);
    }

    @Getter
    @Builder
    public static class CurrentAppUser {

        private int id;
        private String username;
        private String userAccessStatus;
    }

    private String getUserAccessStatus(final CustomUserDetails currentAppUser) {
        final ArrayList<GrantedAuthority> authorities = new ArrayList<>(currentAppUser.getAuthorities());
        if (authorities.get(0).getAuthority() == null) {
            throw new UnauthorizedException(NOT_PERMITTED);
        }
        return authorities.get(0).getAuthority();
    }
}
