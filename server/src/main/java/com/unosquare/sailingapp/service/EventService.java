package com.unosquare.sailingapp.service;

import com.unosquare.sailingapp.configuration.Mapper;
import com.unosquare.sailingapp.dto.CreateEventDTO;
import com.unosquare.sailingapp.dto.EventDTO;
import com.unosquare.sailingapp.entity.Event;
import com.unosquare.sailingapp.exception.ResourceNotFoundException;
import com.unosquare.sailingapp.model.UpdateEventViewModel;
import com.unosquare.sailingapp.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final Mapper mapper;

    public List<EventDTO> getAllEvents(){
        final List<Event> eventList = eventRepository.findAll();
        final List<EventDTO> eventDTOList = mapper.map(eventList, EventDTO.class);
        return eventDTOList;
    }

    public EventDTO getEventByID(final int id){
        final Event event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event Cannot be found, ensure the ID is valid."));
        final EventDTO eventDTO = mapper.map(event, EventDTO.class);
        return eventDTO;
    }

    public EventDTO updateEvent(final int id, final UpdateEventViewModel updateEventViewModel){
        final Event event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event cannot be found, ensure the ID is valid"));
        mapper.map(updateEventViewModel, event);
        eventRepository.save(event);
        return mapper.map(event, EventDTO.class);
    }

    public EventDTO createEvent(final CreateEventDTO createEventDTO){
        final Event event = mapper.map(createEventDTO, Event.class);
        eventRepository.save(event);
        return mapper.map(event, EventDTO.class);
    }
}
