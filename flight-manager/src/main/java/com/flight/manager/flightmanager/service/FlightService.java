package com.flight.manager.flightmanager.service;

import java.util.List;

import com.flight.manager.flightmanager.dto.FlightDTO;

public interface FlightService {

    List<FlightDTO> getAllFlights();

    FlightDTO addFlight(FlightDTO flightDTO);

    FlightDTO updateFlight(FlightDTO flightDTO);

    FlightDTO getById ( Long id );

    FlightDTO deleteById (Long id);
    
}
