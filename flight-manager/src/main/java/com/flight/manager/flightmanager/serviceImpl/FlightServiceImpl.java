package com.flight.manager.flightmanager.serviceImpl;

import com.flight.manager.flightmanager.repository.FlightRepository;
import com.flight.manager.flightmanager.service.FlightService;
import com.flight.manager.flightmanager.model.Flight;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flight.manager.flightmanager.dto.FlightDTO;
import com.flight.manager.flightmanager.mapper.FlightMapper;

@Service
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

    @Override 
    public FlightDTO updateFlight(FlightDTO flightDTO)  {
    Optional<Flight> existingFlight = flightRepository.findById(flightDTO.getId());
    
    if (!existingFlight.isPresent()) {
      // TODO Exception here
    }
    
    Flight updatedFlight = flightRepository.save(flightMapper.toFlightEntity(flightDTO));
    
    return flightMapper.toFlightDTO(updatedFlight);
}

@Override
public FlightDTO  getById ( Long id ){


    Optional<Flight> foundFlight = flightRepository.findById(id);
    if(foundFlight.isEmpty())
    {
        //todo Throw an error
    }

    return flightMapper.toFlightDTO(foundFlight.get());
}

@Override 

public FlightDTO deleteById(Long id)  {
    Optional<Flight> existingFlight = flightRepository.findById(id);
    
    if (!existingFlight.isPresent()) {
       //todo : throw exception
    }
    
    flightRepository.deleteById(id);
    
    return flightMapper.toFlightDTO(existingFlight.get());
}
}
