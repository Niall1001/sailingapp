package com.unosquare.sailingapp.service;

import com.unosquare.sailingapp.configuration.Mapper;
import com.unosquare.sailingapp.dto.AppUserDTO;
import com.unosquare.sailingapp.dto.CreateAppUserDTO;
import com.unosquare.sailingapp.entity.AppUser;
import com.unosquare.sailingapp.entity.UserAccessStatus;
import com.unosquare.sailingapp.exception.ResourceNotFoundException;
import com.unosquare.sailingapp.model.UpdateAppUserViewModel;
import com.unosquare.sailingapp.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private static final int STAGED_USER_STATUS = 1;
    private static final int ACTIVE_USER_STATUS = 2;
    private static final String USER_NOT_FOUND = "User not found";
    private final AppUserRepository appUserRepository;
    private final Mapper mapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EntityManager entityManager;
    public List<AppUserDTO> getAllAppUsers(){
        final List<AppUser> appUserList = appUserRepository.findAll();
        final List<AppUserDTO> appUserDTOList = mapper.map(appUserList, AppUserDTO.class);
        return appUserDTOList;
    }

    public AppUserDTO getAppUserByID(final int id){
        final AppUser appUser = appUserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("App User Cannot be found, ensure the ID is valid."));
        final AppUserDTO appUserDTO = mapper.map(appUser, AppUserDTO.class);
        return appUserDTO;
    }

    public AppUserDTO createAppUser(final CreateAppUserDTO createAppUserDTO){
        final AppUser appUser = mapper.map(createAppUserDTO, AppUser.class);
        appUser.setPassword(bCryptPasswordEncoder.encode(createAppUserDTO.getPassword()));
        appUser.setUserAccessStatus(entityManager.getReference(UserAccessStatus.class, ACTIVE_USER_STATUS));
        appUserRepository.save(appUser);
        return mapper.map(appUser, AppUserDTO.class);
    }
    public AppUserDTO updateAppUser(final int id, final UpdateAppUserViewModel updateAppUserViewModel){
        final AppUser appUser = appUserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("App user cannot be found, ensure the user exists before updating."));
        mapper.map(updateAppUserViewModel, appUser);
        appUserRepository.save(appUser);
        return mapper.map(appUser, AppUserDTO.class);}
    public void deleteAppUser(final int id){
        appUserRepository.deleteById(id);}
}
