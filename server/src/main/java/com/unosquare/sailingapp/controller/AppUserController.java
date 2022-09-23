package com.unosquare.sailingapp.controller;

import com.unosquare.sailingapp.configuration.Mapper;
import com.unosquare.sailingapp.dto.AppUserDTO;
import com.unosquare.sailingapp.dto.CreateAppUserDTO;
import com.unosquare.sailingapp.model.AppUserViewModel;
import com.unosquare.sailingapp.model.CreateAppUserViewModel;
import com.unosquare.sailingapp.model.UpdateAppUserViewModel;
import com.unosquare.sailingapp.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/app-users")
public class AppUserController {

    private final AppUserService appUserService;
    private final Mapper mapper;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppUserViewModel>> GetAllAppUsers(){
        List<AppUserDTO> appUserDTOList = appUserService.getAllAppUsers();
        List<AppUserViewModel> appUserViewModelList = mapper.map(appUserDTOList, AppUserViewModel.class);
        return ResponseEntity.ok(appUserViewModelList);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity GetAppUsersById(@PathVariable final int id){
        AppUserDTO appUserDTO = appUserService.getAppUserByID(id);
        AppUserViewModel appUserViewModel = mapper.map(appUserDTO, AppUserViewModel.class);
        return ResponseEntity.ok(appUserViewModel);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity CreateAppUser(@Valid @RequestBody(required = false) final CreateAppUserViewModel createAppUserViewModel){
        final CreateAppUserDTO createAppUserDTO = mapper.map(createAppUserViewModel, CreateAppUserDTO.class);
        AppUserDTO appUserDTO = appUserService.createAppUser(createAppUserDTO);
        AppUserViewModel appUserViewModel = mapper.map(appUserDTO, AppUserViewModel.class);
        return new ResponseEntity(appUserViewModel, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity UpdateAppUserById(@PathVariable final int id, @RequestBody final UpdateAppUserViewModel updateAppUserViewModel){
        final AppUserDTO appuserDTO = appUserService.updateAppUser(id, updateAppUserViewModel);
        return new ResponseEntity(appuserDTO, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity DeleteAppUserById(@PathVariable final int id) {
        appUserService.deleteAppUser(id);
        return ResponseEntity.noContent().build();
    }
}
