package com.flight.manager.flightmanager.serviceImpl;

import com.flight.manager.flightmanager.repository.AirlineRepo;
import com.flight.manager.flightmanager.repository.FlightRepository;
import com.flight.manager.flightmanager.repository.ReservationRepository;
import com.flight.manager.flightmanager.repository.UserRepo;
import com.flight.manager.flightmanager.service.FlightService;

import jakarta.persistence.EntityNotFoundException;

import com.flight.manager.flightmanager.model.Airline;
import com.flight.manager.flightmanager.model.Flight;
import com.flight.manager.flightmanager.model.Reservation;
import com.flight.manager.flightmanager.model.StatusEnum;
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
    private final AirlineRepo airlineRepo;
    private final ReservationRepository reservationRepository;

    FlightServiceImpl( FlightRepository flightRepository , FlightMapper flightMapper, UserRepo userRepo, AirlineRepo airlineRepo, ReservationRepository reservationRepository)
    {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
        this.userRepo = userRepo;
        this.airlineRepo = airlineRepo;
        this.reservationRepository =reservationRepository;
    }

    @Override
    public List<FlightDTO> getAllFlights(){

        List<Flight> flights = flightRepository.getAllFlights();
        return flightMapper.mapEntitiesToDTOs(flights);
    }


    @Override
    public FlightDTO addFlight(FlightDTO flightDTO){


        Optional<Airline> air = airlineRepo.getAirlineByName(flightDTO.getAirlineName());
        if(air.isEmpty()){
            throw new EntityNotFoundException("No such entity");
        }
       
        Flight to_save = flightMapper.toFlightEntity(flightDTO, air.get());
        System.out.println(flightDTO.getFlightNumber());
        System.out.println(to_save.getFlightNumber());
        to_save.setDeleted(false);
        Flight saved = flightRepository.save(to_save);

        return flightMapper.toFlightDTO(saved);

    }



@Override
public FlightDTO  getById ( Long id ){


    Optional<Flight> foundFlight = flightRepository.findById(id);
    if(foundFlight.isEmpty())
    {
       throw new EntityNotFoundException();
    }

    return flightMapper.toFlightDTO(foundFlight.get());
}

@Override 

public FlightDTO deleteById(Long id)  {
    Optional<Flight> existingFlight = flightRepository.findById(id);
    
    if (!existingFlight.isPresent()) {
       throw new EntityNotFoundException("No such flght");
    }
    
    existingFlight.get().setDeleted(true);
    flightRepository.save(existingFlight.get());
    
    updateReservations(existingFlight.get());
    return flightMapper.toFlightDTO(existingFlight.get());
}


@Override
public List<FlightDTO> getAllAvailableFlightsBetween(String source , String destinationAirportCode){

    List<FlightDTO> result = new ArrayList<>();
    List<Flight> flights = flightRepository.getAllAvailableFlightsBetween(source,destinationAirportCode);

    result = flightMapper.mapEntitiesToDTOs(flights);

    return result;
}

private void updateReservations (Flight f ){
    List<Reservation> res = reservationRepository.getReservations(f.getId());
    for (Reservation res1 : res){
        res1.setStatus(StatusEnum.CANCELED);
        reservationRepository.save(res1);
    }
}

@Override
public List<Flight> getFilteredFlights(LocalDateTime departureDate, String sourceAirportCode , String destinationAirportCode) {
    Specification<Flight> spec = Specification.where(null);

        if (departureDate != null) {
           final  LocalDateTime  limit = (departureDate.plusDays(1)).minusMinutes(1);
           
            spec = spec.and((root, query, builder) -> builder.between(root.get("departureTime"), departureDate, limit));
        }

        if (!sourceAirportCode.isBlank()) {
            spec = spec.and((root, query, builder) -> builder.equal(root.get("sourceAirportCode"), sourceAirportCode));
        }

        if (!destinationAirportCode.isBlank()) {
            spec = spec.and((root, query, builder) -> builder.equal(root.get("destinationAirportCode"), destinationAirportCode));
        }

        spec = spec.and((root, query, builder) -> builder.equal(root.get("isDeleted"), false));

        return flightRepository.findAll(spec);
}
}
