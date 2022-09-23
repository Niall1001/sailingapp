package com.unosquare.sailingapp.controller;

import com.unosquare.sailingapp.configuration.Mapper;
import com.unosquare.sailingapp.dto.BoatDTO;
import com.unosquare.sailingapp.dto.CreateBoatDTO;
import com.unosquare.sailingapp.model.BoatViewModel;
import com.unosquare.sailingapp.model.CreateBoatViewModel;
import com.unosquare.sailingapp.model.UpdateBoatViewModel;
import com.unosquare.sailingapp.service.BoatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/boats")
public class BoatController {

    private final BoatService boatService;
    private final Mapper mapper;

    @GetMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BoatViewModel>> GetAllBoats(){
        List<BoatDTO> boatDTOList = boatService.getAllBoats();
        List<BoatViewModel> boatViewModelList = mapper.map(boatDTOList, BoatViewModel.class);
        return ResponseEntity.ok(boatViewModelList);
    }

    @GetMapping(value="/{boatId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity GetBoatById(@PathVariable final int boatId){
        BoatDTO boatDTO = boatService.getBoatByID(boatId);
        BoatViewModel boatViewModel = mapper.map(boatDTO, BoatViewModel.class);
        return ResponseEntity.ok(boatViewModel);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity CreateBoat(@Valid @RequestBody(required = false) final CreateBoatViewModel createBoatViewModel){
        final CreateBoatDTO createBoatDTO = mapper.map(createBoatViewModel, CreateBoatDTO.class);
        BoatDTO boatDTO = boatService.createBoat(createBoatDTO);
        BoatViewModel boatViewModel = mapper.map(boatDTO, BoatViewModel.class);
        return new ResponseEntity(boatViewModel, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity UpdateBoatById(@PathVariable final int id, @RequestBody final UpdateBoatViewModel updateBoatViewModel) {
        final BoatDTO boatDTO = boatService.updateBoat(id, updateBoatViewModel);
        return new ResponseEntity(boatDTO, HttpStatus.CREATED);
    }


    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity DeleteBoatById(@PathVariable final int id) {
        boatService.deleteBoat(id);
        return ResponseEntity.noContent().build();
    }



}
