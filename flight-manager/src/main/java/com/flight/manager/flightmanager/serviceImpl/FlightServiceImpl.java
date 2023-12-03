package com.flight.manager.flightmanager.serviceImpl;

import com.flight.manager.flightmanager.repository.FlightRepository;
import com.flight.manager.flightmanager.service.FlightService;
import com.flight.manager.flightmanager.model.Flight;
import java.util.List;
import java.util.Optional;

import com.flight.manager.flightmanager.dto.FlightDTO;
import com.flight.manager.flightmanager.mapper.FlightMapper;
public class FlightServiceImpl implements FlightService {
    
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper; 

    FlightServiceImpl( FlightRepository flightRepository , FlightMapper flightMapper)
    {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    @Override
    public List<FlightDTO> getAllFlights(){

        List<Flight> flights = flightRepository.findAll();
        return flightMapper.mapEntitiesToDTOs(flights);
    }


    @Override
    public FlightDTO addFlight(FlightDTO flightDTO){

        Optional<Flight> check = flightRepository.findById(flightDTO.getId());
        if (check.isPresent()){
            // TODO : throw an exception
        }

        Flight saved = flightRepository.save(flightMapper.toFlightEntity(flightDTO));

        return flightMapper.toFlightDTO(saved);

    }
}
