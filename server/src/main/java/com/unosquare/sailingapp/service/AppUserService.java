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

    private static final int ADMIN_USER = 3;
    private static final int BOAT_OWNER_USER = 4;
    private static final int CREW_USER = 5;
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
        if(createAppUserDTO.getUser_type() == 1)
            appUser.setUserAccessStatus(entityManager.getReference(UserAccessStatus.class, ADMIN_USER));
        else if(createAppUserDTO.getUser_type() == 2)
            appUser.setUserAccessStatus(entityManager.getReference(UserAccessStatus.class, BOAT_OWNER_USER));
        else if(createAppUserDTO.getUser_type() == 3)
            appUser.setUserAccessStatus(entityManager.getReference(UserAccessStatus.class, CREW_USER));
        else
            appUser.setUserAccessStatus(entityManager.getReference(UserAccessStatus.class, ACTIVE_USER_STATUS));
        appUserRepository.save(appUser);
        return mapper.map(appUser, AppUserDTO.class);
    }
    public AppUserDTO updateAppUser(final int id, final UpdateAppUserViewModel updateAppUserViewModel){
        final AppUser appUser = appUserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("App user cannot be found, ensure the user exists before updating."));
        mapper.map(updateAppUserViewModel, appUser);
        appUser.setPassword(bCryptPasswordEncoder.encode(updateAppUserViewModel.getPassword()));
        if(updateAppUserViewModel.getUser_type() == 1)
            appUser.setUserAccessStatus(entityManager.getReference(UserAccessStatus.class, ADMIN_USER));
        else if(updateAppUserViewModel.getUser_type() == 2)
            appUser.setUserAccessStatus(entityManager.getReference(UserAccessStatus.class, BOAT_OWNER_USER));
        else if(updateAppUserViewModel.getUser_type() == 3)
            appUser.setUserAccessStatus(entityManager.getReference(UserAccessStatus.class, CREW_USER));
        else
            appUser.setUserAccessStatus(entityManager.getReference(UserAccessStatus.class, ACTIVE_USER_STATUS));
        appUserRepository.save(appUser);
        return mapper.map(appUser, AppUserDTO.class);}
    public void deleteAppUser(final int id){
        appUserRepository.deleteById(id);}
}
