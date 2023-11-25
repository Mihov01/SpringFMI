package com.flight.manager.flightmanager.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.flight.manager.flightmanager.dto.AirlineDTO;
import com.flight.manager.flightmanager.model.Airline;

@Component
public class AirlineMapper {
    private final ModelMapper modelMapper;

    public AirlineMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AirlineDTO toAirlineDTO(Airline airline) {
        return modelMapper.map(airline, AirlineDTO.class);
    }

    public Airline toAirlineEntity(AirlineDTO airLineDTO) {
        return modelMapper.map(airLineDTO, Airline.class);
    }
    
     public List<AirlineDTO> mapEntitiesToDTOs(List<Airline> entities) {
        return entities.stream()
                .map(entity -> modelMapper.map(entity, AirlineDTO.class))
                .collect(Collectors.toList());
    }
  
}