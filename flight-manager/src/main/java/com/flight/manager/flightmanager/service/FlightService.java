package com.flight.manager.flightmanager.service;

import java.time.LocalDateTime;
import java.util.List;

import com.flight.manager.flightmanager.dto.FlightDTO;
import com.flight.manager.flightmanager.model.Flight;
import com.flight.manager.flightmanager.model.User;

public interface FlightService {

    List<FlightDTO> getAllFlights();

    List<Flight> getFilteredFlights(LocalDateTime departureDate, String sourceAirportCode , String destinationAirportCode);

    FlightDTO addFlight(FlightDTO flightDTO);

    FlightDTO updateFlight(FlightDTO flightDTO);

    FlightDTO getById ( Long id );

    FlightDTO deleteById (Long id);

   List<FlightDTO> getAllAvailableFlightsBetween(String source , String destinationAirportCode);
}
