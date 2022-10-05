package com.unosquare.sailingapp.controller;

import com.unosquare.sailingapp.configuration.Mapper;
import com.unosquare.sailingapp.dto.CreateAuthenticationDTO;
import com.unosquare.sailingapp.dto.GrabAuthenticationDTO;
import com.unosquare.sailingapp.model.AuthenticationViewModel;
import com.unosquare.sailingapp.model.CreateAuthenticationViewModel;
import com.unosquare.sailingapp.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
@Tag(name = "Authentication Controller")
public class AuthenticationController {

    private final Mapper mapper;
    private final AuthenticationService authenticationService;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthenticationViewModel> createAuthenticationToken(@Valid @RequestBody final CreateAuthenticationViewModel createAuthenticationViewModel){
        final CreateAuthenticationDTO createAuthenticationDTO = mapper.map(createAuthenticationViewModel, CreateAuthenticationDTO.class);
        final GrabAuthenticationDTO grabAuthenticationDTO = authenticationService.createAuthenticationToken(createAuthenticationDTO);
        final AuthenticationViewModel authenticationViewModel = mapper.map(grabAuthenticationDTO, AuthenticationViewModel.class);
        return new ResponseEntity<>(authenticationViewModel, HttpStatus.CREATED);
    }
}
