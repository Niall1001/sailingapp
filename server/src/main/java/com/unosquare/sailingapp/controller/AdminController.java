package com.unosquare.sailingapp.controller;

import com.unosquare.sailingapp.configuration.Mapper;
import com.unosquare.sailingapp.dto.AdminDTO;
import com.unosquare.sailingapp.dto.CreateAdminDTO;
import com.unosquare.sailingapp.model.AdminViewModel;
import com.unosquare.sailingapp.model.CreateAdminViewModel;
import com.unosquare.sailingapp.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins")
public class AdminController {
    private final AdminService adminService;
    private final Mapper mapper;
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity GetAllAdmins(){
        List<AdminDTO> adminDTOList = adminService.getAllAdmins();
        List<AdminViewModel> adminViewModelList = mapper.map(adminDTOList, AdminViewModel.class);
        return ResponseEntity.ok(adminViewModelList);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity GetAdminsById(@PathVariable final int id){
        AdminDTO adminDTO = adminService.getAdminByID(id);
        AdminViewModel adminViewModel = mapper.map(adminDTO, AdminViewModel.class);
        return ResponseEntity.ok(adminViewModel);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity CreateAdmin(@Valid @RequestBody(required = false) final CreateAdminViewModel createAdminViewModel){
        final CreateAdminDTO createAdminDTO = mapper.map(createAdminViewModel, CreateAdminDTO.class);
        AdminDTO adminDTO = adminService.createAdmin(createAdminDTO);
        AdminViewModel adminViewModel = mapper.map(adminDTO, AdminViewModel.class);
        return new ResponseEntity(adminViewModel, HttpStatus.CREATED);
    }
}
