package com.flight.manager.flightmanager.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.flight.manager.flightmanager.dto.FlightDTO;
import com.flight.manager.flightmanager.model.Airline;
import com.flight.manager.flightmanager.model.Flight;

@Component
public class FlightMapper {
    private final ModelMapper modelMapper;

    public FlightMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public FlightDTO toFlightDTO(Flight flight) {
        FlightDTO res = new FlightDTO();

        res.setAirlineName(flight.getAirline().getName());
        res.setArrivalTime(flight.getArrivalTime());
        res.setAvailableSeats(flight.getAvailableSeats());
        res.setDepartureTime(flight.getDepartureTime());
        res.setDestinationAirportCode(flight.getDestinationAirportCode());
       res.setFlightNumber(flight.getFlightNumber());
       res.setId(flight.getId());
       res.setPrice(flight.getPrice());
       res.setSourceAirportCode(flight.getSourceAirportCode());
       return res;
    }

    public Flight toFlightEntity(FlightDTO flightDto, Airline airline) {
            Flight f = new Flight();
            f.setAirline(airline);
            f.setArrivalTime(flightDto.getArrivalTime());
            f.setAvailableSeats(flightDto.getAvailableSeats());
            f.setDepartureTime(flightDto.getDepartureTime());
            f.setDestinationAirportCode(flightDto.getDestinationAirportCode());
            f.setFlightNumber(flightDto.getFlightNumber());
            f.setId(flightDto.getId());
            f.setPrice(flightDto.getPrice());
            f.setSourceAirportCode(flightDto.getSourceAirportCode());

            return f;
    }
    
     public List<FlightDTO> mapEntitiesToDTOs(List<Flight> entities) {
        return entities.stream()
                .map(entity -> modelMapper.map(entity, FlightDTO.class))
                .collect(Collectors.toList());
    }
  
}