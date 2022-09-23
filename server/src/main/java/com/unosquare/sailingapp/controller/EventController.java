package com.unosquare.sailingapp.controller;

import com.unosquare.sailingapp.configuration.Mapper;
import com.unosquare.sailingapp.dto.CreateEventDTO;
import com.unosquare.sailingapp.dto.EventDTO;
import com.unosquare.sailingapp.model.CreateEventViewModel;
import com.unosquare.sailingapp.model.EventViewModel;
import com.unosquare.sailingapp.model.UpdateEventViewModel;
import com.unosquare.sailingapp.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final Mapper mapper;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity GetAllEvents(){
        List<EventDTO> eventDTOList = eventService.getAllEvents();
        List<EventViewModel> eventViewModelList = mapper.map(eventDTOList, EventViewModel.class);
        return ResponseEntity.ok(eventViewModelList);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity GetEventById(@PathVariable final int id){
        EventDTO eventDTO = eventService.getEventByID(id);
        EventViewModel eventViewModel = mapper.map(eventDTO, EventViewModel.class);
        return ResponseEntity.ok(eventViewModel);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity CreateEvent(@Valid @RequestBody(required = false) final CreateEventViewModel createEventViewModel){
        final CreateEventDTO createEventDTO = mapper.map(createEventViewModel, CreateEventDTO.class);
        EventDTO eventDTO = eventService.createEvent(createEventDTO);
        EventViewModel eventViewModel = mapper.map(eventDTO, EventViewModel.class);
        return new ResponseEntity(eventViewModel, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity UpdateEventById(@PathVariable final int id, @RequestBody final UpdateEventViewModel updateEventViewModel){
        final EventDTO eventDTO = eventService.updateEvent(id, updateEventViewModel);
        return new ResponseEntity(eventDTO, HttpStatus.CREATED);
    }

}
