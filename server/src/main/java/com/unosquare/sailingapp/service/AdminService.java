package com.unosquare.sailingapp.service;

import com.unosquare.sailingapp.configuration.Mapper;
import com.unosquare.sailingapp.dto.AdminDTO;
import com.unosquare.sailingapp.dto.CreateAdminDTO;
import com.unosquare.sailingapp.entity.Admin;
import com.unosquare.sailingapp.exception.ResourceNotFoundException;
import com.unosquare.sailingapp.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final Mapper mapper;

    public List<AdminDTO> getAllAdmins(){
        final List<Admin> adminList = adminRepository.findAll();
        final List<AdminDTO> adminDTOList = mapper.map(adminList, AdminDTO.class);
        return adminDTOList;
    }

    public AdminDTO getAdminByID(final int id){
        final Admin admin = adminRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Admin Account Cannot be found, ensure the user ID is a valid admin account."));
        final AdminDTO adminDTO = mapper.map(admin, AdminDTO.class);
        return adminDTO;
    }

    public AdminDTO createAdmin(final CreateAdminDTO createAdminDTO){
        final Admin admin = mapper.map(createAdminDTO, Admin.class);
        adminRepository.save(admin);
        return mapper.map(admin, AdminDTO.class);
    }




}
