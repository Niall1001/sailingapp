package com.unosquare.sailingapp.security;

import com.unosquare.sailingapp.entity.AppUser;
import com.unosquare.sailingapp.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserSecurityService implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(final String emailAddress){
        final Optional<AppUser> currentUser = appUserRepository.findAppUserByEmail(emailAddress);
        if(currentUser.isPresent()){
            return getUserDetails(currentUser.get());
        }
        try {
            throw new Exception(String.format("Incorrect email (%s) and/or password", emailAddress));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private UserDetails getUserDetails(final AppUser currentUser){
        return new CustomUserDetails(
                currentUser.getId(), currentUser.getEmailAddress(), currentUser.getPassword(), getAuthorities(currentUser)) {
        };
            
        }

    private Collection<? extends GrantedAuthority> getAuthorities(final AppUser currentUser) {
        return List.of(new SimpleGrantedAuthority(currentUser.getName()));
}
}
