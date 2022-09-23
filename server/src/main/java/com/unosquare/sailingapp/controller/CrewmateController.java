package com.unosquare.sailingapp.controller;

import com.unosquare.sailingapp.configuration.Mapper;
import com.unosquare.sailingapp.dto.CreateCrewMateDTO;
import com.unosquare.sailingapp.dto.CrewMateDTO;
import com.unosquare.sailingapp.model.CreateCrewMateViewModel;
import com.unosquare.sailingapp.model.CreateEventViewModel;
import com.unosquare.sailingapp.model.CrewmateViewModel;
import com.unosquare.sailingapp.service.CrewMateService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/crews")
public class CrewmateController {
    private final CrewMateService crewMateService;
    private final Mapper mapper;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity GetAllCrewmates(){
        List<CrewMateDTO> crewMateDTOList = crewMateService.getAllCrewMates();
        List<CrewmateViewModel> crewmateViewModelList = mapper.map(crewMateDTOList, CrewmateViewModel.class);
        return ResponseEntity.ok(crewmateViewModelList);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity GetCrewmateById(@PathVariable final int id){
        CrewMateDTO crewMateDTO = crewMateService.getCrewMateByID(id);
        CrewmateViewModel crewmateViewModel = mapper.map(crewMateDTO, CrewmateViewModel.class);
        return ResponseEntity.ok(crewmateViewModel);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity CreateCrewmate(@Valid @RequestBody(required = false) final CreateCrewMateViewModel createCrewMateViewModel){
        final CreateCrewMateDTO createCrewMateDTO = mapper.map(createCrewMateViewModel, CreateCrewMateDTO.class);
        CrewMateDTO crewMateDTO = crewMateService.createCrewMate(createCrewMateDTO);
        CrewmateViewModel crewmateViewModel = mapper.map(crewMateDTO, CrewmateViewModel.class);
        return new ResponseEntity(crewmateViewModel, HttpStatus.CREATED);
    }
}
