package com.flight.manager.flightmanager.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.flight.manager.flightmanager.dto.FlightDTO;
import com.flight.manager.flightmanager.model.Flight;

@Component
public class FlightMapper {
    private final ModelMapper modelMapper;

    public FlightMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public FlightDTO toFlightDTO(Flight airline) {
        return modelMapper.map(airline, FlightDTO.class);
    }

    public Flight toFlightEntity(FlightDTO airLineDTO) {
        return modelMapper.map(airLineDTO, Flight.class);
    }
    
     public List<FlightDTO> mapEntitiesToDTOs(List<Flight> entities) {
        return entities.stream()
                .map(entity -> modelMapper.map(entity, FlightDTO.class))
                .collect(Collectors.toList());
    }
  
}