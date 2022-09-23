package com.unosquare.sailingapp.controller;

import com.unosquare.sailingapp.configuration.Mapper;
import com.unosquare.sailingapp.dto.CreateEventBoatDTO;
import com.unosquare.sailingapp.dto.EventBoatDTO;
import com.unosquare.sailingapp.model.CreateEventBoatViewModel;
import com.unosquare.sailingapp.model.EventBoatViewModel;
import com.unosquare.sailingapp.model.UpdateEventBoatViewModel;
import com.unosquare.sailingapp.service.EventBoatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events-boats")
public class EventBoatController {
    private final EventBoatService eventBoatService;
    private final Mapper mapper;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity GetAllEventsBoats(){
        List<EventBoatDTO> eventBoatDTOList = eventBoatService.getAllEventBoats();
        List<EventBoatViewModel> eventBoatViewModelList = mapper.map(eventBoatDTOList, EventBoatViewModel.class);
        return ResponseEntity.ok(eventBoatViewModelList);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity GetEventById(@PathVariable final int id){
        EventBoatDTO eventBoatDTO = eventBoatService.getEventBoatByID(id);
        EventBoatViewModel eventBoatViewModel = mapper.map(eventBoatDTO, EventBoatViewModel.class);
        return ResponseEntity.ok(eventBoatViewModel);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity CreateEvent(@Valid @RequestBody(required = false) final CreateEventBoatViewModel createEventBoatViewModel) {
        final CreateEventBoatDTO createEventBoatDTO = mapper.map(createEventBoatViewModel, CreateEventBoatDTO.class);
        EventBoatDTO eventBoatDTO = eventBoatService.createEventBoat(createEventBoatDTO);
        EventBoatViewModel eventBoatViewModel = mapper.map(eventBoatDTO, EventBoatViewModel.class);
        return new ResponseEntity(eventBoatViewModel, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity UpdateEventById(@PathVariable final int id, @RequestBody final UpdateEventBoatViewModel updateEventBoatViewModel) {
        final EventBoatDTO eventBoatDTO = eventBoatService.updateEventBoat(id, updateEventBoatViewModel);
        return new ResponseEntity(eventBoatDTO, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity DeleteAppUserById(@PathVariable final int id) {
        eventBoatService.deleteEventBoat(id);
        return ResponseEntity.noContent().build();
    }

    }
