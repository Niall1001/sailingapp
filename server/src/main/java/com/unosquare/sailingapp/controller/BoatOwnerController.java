package com.unosquare.sailingapp.controller;

import com.unosquare.sailingapp.configuration.Mapper;
import com.unosquare.sailingapp.dto.BoatOwnerDTO;
import com.unosquare.sailingapp.dto.CreateBoatOwnerDTO;
import com.unosquare.sailingapp.model.BoatOwnerViewModel;
import com.unosquare.sailingapp.model.CreateBoatOwnerViewModel;
import com.unosquare.sailingapp.service.BoatOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boat-owners")
public class BoatOwnerController {
    private final BoatOwnerService boatOwnerService;
    private final Mapper mapper;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity GetAllBoatOwners(){
        List<BoatOwnerDTO> boatOwnerDTOList = boatOwnerService.getAllBoatOwners();
        List<BoatOwnerViewModel> boatOwnerViewModelList = mapper.map(boatOwnerDTOList, BoatOwnerViewModel.class);
        return ResponseEntity.ok(boatOwnerViewModelList);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity GetBoatOwnerByID(@PathVariable final int id){
        BoatOwnerDTO boatOwnerDTO = boatOwnerService.getBoatOwnerByID(id);
        BoatOwnerViewModel boatOwnerViewModel = mapper.map(boatOwnerDTO, BoatOwnerViewModel.class);
        return ResponseEntity.ok(boatOwnerViewModel);
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity CreateBoatOwner(@Valid @RequestBody(required = false) final CreateBoatOwnerViewModel createBoatOwnerViewModel){
        final CreateBoatOwnerDTO createBoatOwnerDTO = mapper.map(createBoatOwnerViewModel, CreateBoatOwnerDTO.class);
        BoatOwnerDTO boatOwnerDTO = boatOwnerService.createBoatOwner(createBoatOwnerDTO);
        BoatOwnerViewModel boatOwnerViewModel = mapper.map(boatOwnerDTO, BoatOwnerViewModel.class);
        return new ResponseEntity(boatOwnerViewModel, HttpStatus.CREATED);
    }
}
