package com.unosquare.sailingapp.service;

import com.unosquare.sailingapp.configuration.Mapper;
import com.unosquare.sailingapp.dto.CreateEventBoatDTO;
import com.unosquare.sailingapp.dto.EventBoatDTO;
import com.unosquare.sailingapp.entity.EventBoat;
import com.unosquare.sailingapp.exception.ResourceNotFoundException;
import com.unosquare.sailingapp.model.UpdateEventBoatViewModel;
import com.unosquare.sailingapp.repository.EventBoatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventBoatService {
    private final EventBoatRepository eventBoatRepository;
    private final Mapper mapper;

    public List<EventBoatDTO> getAllEventBoats(){
        final List<EventBoat> eventBoatList = eventBoatRepository.findAll();
        final List<EventBoatDTO> eventBoatDTOList = mapper.map(eventBoatList, EventBoatDTO.class);
        return eventBoatDTOList;
    }

    public EventBoatDTO getEventBoatByID(final int id){
        final EventBoat eventBoat = eventBoatRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event Boat Association cannot be found."));
        final EventBoatDTO eventBoatDTO = mapper.map(eventBoat, EventBoatDTO.class);
        return eventBoatDTO;
    }

    public EventBoatDTO updateEventBoat(final int id, final UpdateEventBoatViewModel updateEventBoatViewModel){
        final EventBoat eventBoat = eventBoatRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event Boat Association cannot be found"));
        mapper.map(updateEventBoatViewModel, eventBoat);
        eventBoatRepository.save(eventBoat);
        return mapper.map(eventBoat, EventBoatDTO.class);
    }
    public EventBoatDTO createEventBoat(final CreateEventBoatDTO createEventBoatDTO){
        final EventBoat eventBoat = mapper.map(createEventBoatDTO, EventBoat.class);
        eventBoatRepository.save(eventBoat);
        return mapper.map(eventBoat, EventBoatDTO.class);
    }

    public void deleteEventBoat(final int id){
        eventBoatRepository.deleteById(id);
    }
}