package com.flight.manager.flightmanager.mapper;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.flight.manager.flightmanager.dto.AirportDTO;
import com.flight.manager.flightmanager.model.Airport;

@Component
public class AirportMapper {
    private final ModelMapper modelMapper;

    public AirportMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AirportDTO toAirportDTO(Airport airport) {
        return modelMapper.map(airport, AirportDTO.class);
    }

    public Airport toAirportEntity(AirportDTO airportDTO) {
        return modelMapper.map(airportDTO, Airport.class);
    }

    public List<AirportDTO> mapEntitiesToDTOs(List<Airport> entities) {
        return entities.stream()
                .map(entity -> modelMapper.map(entity, AirportDTO.class))
                .collect(Collectors.toList());
    }
}
