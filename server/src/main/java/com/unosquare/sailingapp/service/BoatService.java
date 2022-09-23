package com.unosquare.sailingapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unosquare.sailingapp.exception.InternalServerErrorException;
import com.unosquare.sailingapp.exception.ResourceNotFoundException;
import com.unosquare.sailingapp.model.UpdateBoatViewModel;
import com.unosquare.sailingapp.repository.BoatRepository;
import com.unosquare.sailingapp.configuration.Mapper;
import com.unosquare.sailingapp.dto.BoatDTO;
import com.unosquare.sailingapp.dto.CreateBoatDTO;
import com.unosquare.sailingapp.entity.Boat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoatService {

    private final BoatRepository boatRepository;
    private final Mapper mapper;

    private final ObjectMapper objectMapper;

    public List<BoatDTO> getAllBoats(){
        final List<Boat> boatList = boatRepository.findAll();
        final List<BoatDTO> boatDTOList = mapper.map(boatList, BoatDTO.class);
        return boatDTOList;
    }

    public BoatDTO getBoatByID(final int id){
        final Boat boat = boatRepository.findById(id).orElseThrow(() -> new InternalServerErrorException("Boat Cannot be found, ensure the ID is valid."));
        final BoatDTO boatDTO = mapper.map(boat, BoatDTO.class);
        return boatDTO;
    }

    public BoatDTO createBoat(final CreateBoatDTO createBoatDTO) {
        final Boat boat = mapper.map(createBoatDTO, Boat.class);
        boatRepository.save(boat);
        return mapper.map(boat, BoatDTO.class);
    }

    public BoatDTO updateBoat(final int id, final UpdateBoatViewModel updateBoatViewModel){
        final Boat boat = boatRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Boat cannot be found, ensure the boat exists before updating."));
        mapper.map(updateBoatViewModel, boat);
        boatRepository.save(boat);
        return mapper.map(boat, BoatDTO.class);
    }
    public void deleteBoat(final int id){
            boatRepository.deleteById(id);
    }
}
