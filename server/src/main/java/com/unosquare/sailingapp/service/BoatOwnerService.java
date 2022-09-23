package com.unosquare.sailingapp.service;

import com.unosquare.sailingapp.configuration.Mapper;
import com.unosquare.sailingapp.dto.BoatOwnerDTO;
import com.unosquare.sailingapp.dto.CreateBoatOwnerDTO;
import com.unosquare.sailingapp.entity.BoatOwner;
import com.unosquare.sailingapp.exception.ResourceNotFoundException;
import com.unosquare.sailingapp.repository.BoatOwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoatOwnerService {
    private final BoatOwnerRepository boatOwnerRepository;
    private final Mapper mapper;

    public List<BoatOwnerDTO> getAllBoatOwners(){
        final List<BoatOwner> boatOwnerList = boatOwnerRepository.findAll();
        final List<BoatOwnerDTO> boatOwnerDTOList = mapper.map(boatOwnerList, BoatOwnerDTO.class);
        return boatOwnerDTOList;
    }

    public BoatOwnerDTO getBoatOwnerByID(final int id){
        final BoatOwner boatOwner = boatOwnerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Boat Owner association cannot be found, ensure the ID is valid."));
        final BoatOwnerDTO boatOwnerDTO = mapper.map(boatOwner, BoatOwnerDTO.class);
        return boatOwnerDTO;
    }

    public BoatOwnerDTO createBoatOwner(final CreateBoatOwnerDTO createBoatOwnerDTO){
        final BoatOwner boatOwner = mapper.map(createBoatOwnerDTO, BoatOwner.class);
        boatOwnerRepository.save(boatOwner);
        return mapper.map(boatOwner, BoatOwnerDTO.class);

    }
}
