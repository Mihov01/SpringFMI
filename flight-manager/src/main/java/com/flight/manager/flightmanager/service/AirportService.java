package com.flight.manager.flightmanager.service;

import java.util.List;

import com.flight.manager.flightmanager.dto.AirlineDTO;
import com.flight.manager.flightmanager.dto.AirportDTO;

public interface AirportService {
    


      
    List<AirportDTO> getAllAirports();

    AirportDTO createAirport(AirportDTO airportDTO);
    AirportDTO getByCode(String code);
    AirportDTO getByName(String name);
    AirportDTO updateAirport(AirportDTO airline);
    AirportDTO deleteAirport(String code);

}
