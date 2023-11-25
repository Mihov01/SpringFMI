package com.flight.manager.flightmanager.service;
import java.util.List;

import com.flight.manager.flightmanager.dto.AirlineDTO;

public interface AirlineService {
    
    List<AirlineDTO> getAllAirlines();

    AirlineDTO createAirline(AirlineDTO airlineDTO);
    AirlineDTO getById(Long id);
    AirlineDTO getByName(String name);
    AirlineDTO updateAirline(AirlineDTO airline);
    AirlineDTO deleteAirline(Long id);
}
