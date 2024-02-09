package com.flight.manager.flightmanager.serviceImpl;

import com.flight.manager.flightmanager.repository.FlightRepository;
import com.flight.manager.flightmanager.repository.UserRepo;
import com.flight.manager.flightmanager.service.FlightService;
import com.flight.manager.flightmanager.model.Flight;
import com.flight.manager.flightmanager.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.flight.manager.flightmanager.dto.FlightDTO;
import com.flight.manager.flightmanager.mapper.FlightMapper;

@Service
public class FlightServiceImpl implements FlightService {
    
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper; 
    private final UserRepo userRepo;

    FlightServiceImpl( FlightRepository flightRepository , FlightMapper flightMapper, UserRepo userRepo)
    {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
        this.userRepo = userRepo;
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


// TODO make reservaton service and handle it there
@Override
public List<FlightDTO> getAllAvailableFlightsBetween(String source , String destinationAirportCode){

    List<FlightDTO> result = new ArrayList<>();
    List<Flight> flights = flightRepository.getAllAvailableFlightsBetween(source,destinationAirportCode);

    result = flightMapper.mapEntitiesToDTOs(flights);

    return result;
}

@Override
public List<Flight> getFilteredFlights(LocalDateTime departureDate, String sourceAirportCode , String destinationAirportCode) {
    Specification<Flight> spec = Specification.where(null);

        if (departureDate != null) {
            spec = spec.and((root, query, builder) -> builder.equal(root.get("departureTime"), departureDate));
        }

        if (!sourceAirportCode.isBlank()) {
            spec = spec.and((root, query, builder) -> builder.equal(root.get("sourceAirportCode"), sourceAirportCode));
        }

        if (!destinationAirportCode.isBlank()) {
            spec = spec.and((root, query, builder) -> builder.equal(root.get("destinationAirportCode"), destinationAirportCode));
        }

        return flightRepository.findAll(spec);
}
}
