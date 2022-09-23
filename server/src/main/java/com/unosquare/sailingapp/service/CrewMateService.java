package com.unosquare.sailingapp.service;

import com.unosquare.sailingapp.configuration.Mapper;
import com.unosquare.sailingapp.dto.CreateCrewMateDTO;
import com.unosquare.sailingapp.dto.CrewMateDTO;
import com.unosquare.sailingapp.entity.CrewMate;
import com.unosquare.sailingapp.exception.ResourceNotFoundException;
import com.unosquare.sailingapp.repository.CrewMateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CrewMateService {
    private final CrewMateRepository crewMateRepository;
    private final Mapper mapper;

    public List<CrewMateDTO> getAllCrewMates() {
        final List<CrewMate> crewMateList = crewMateRepository.findAll();
        final List<CrewMateDTO> crewMateDTOList = mapper.map(crewMateList, CrewMateDTO.class);
        return crewMateDTOList;
    }

    public CrewMateDTO getCrewMateByID(final int id){
        final CrewMate crewMate = crewMateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Crew Mate Cannot be found, ensure the ID is valid."));
        final CrewMateDTO crewMateDTO = mapper.map(crewMate, CrewMateDTO.class);
        return crewMateDTO;
    }

    public CrewMateDTO createCrewMate(final CreateCrewMateDTO createCrewMateDTO){
        final CrewMate crewMate = mapper.map(createCrewMateDTO, CrewMate.class);
        crewMateRepository.save(crewMate);
        return mapper.map(crewMate, CrewMateDTO.class);
    }
}
