package com.unosquare.sailingapp.service;

import com.unosquare.sailingapp.dto.CreateAuthenticationDTO;
import com.unosquare.sailingapp.dto.GrabAuthenticationDTO;
import com.unosquare.sailingapp.security.UserSecurityService;
import com.unosquare.sailingapp.util.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserSecurityService userSecurityService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public GrabAuthenticationDTO createAuthenticationToken(final CreateAuthenticationDTO createAuthenticationDTO){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                createAuthenticationDTO.getEmailAddress(), createAuthenticationDTO.getPassword()));
        final UserDetails userDetails = userSecurityService.loadUserByUsername(createAuthenticationDTO.getEmailAddress());
        final String jwt = jwtTokenService.generateToken(userDetails);
        final GrabAuthenticationDTO grabAuthenticationDTO = new GrabAuthenticationDTO();
        grabAuthenticationDTO.setJwt(jwt);
        return grabAuthenticationDTO;
    }

}
