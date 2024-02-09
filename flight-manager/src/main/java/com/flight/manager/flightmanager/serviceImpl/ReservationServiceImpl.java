package com.flight.manager.flightmanager.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flight.manager.flightmanager.dto.FlightDTO;
import com.flight.manager.flightmanager.dto.ReservationDTO;
import com.flight.manager.flightmanager.mapper.ReservationMapper;
import com.flight.manager.flightmanager.model.Flight;
import com.flight.manager.flightmanager.model.Reservation;
import com.flight.manager.flightmanager.model.StatusEnum;
import com.flight.manager.flightmanager.model.User;
import com.flight.manager.flightmanager.repository.FlightRepository;
import com.flight.manager.flightmanager.repository.ReservationRepository;
import com.flight.manager.flightmanager.repository.UserRepo;
import com.flight.manager.flightmanager.service.ReservationService;
import com.flight.manager.flightmanager.service.UserService;

import jakarta.persistence.EntityNotFoundException;

@Service

public class ReservationServiceImpl  implements ReservationService{
    

    private final UserRepo userRepo;
    private final  FlightRepository flightRepository;
    private final ReservationMapper reservationMapper;
    private final  ReservationRepository reservationRepository;

    ReservationServiceImpl(UserRepo userRepo, FlightRepository flightRepository, ReservationMapper reservationMapper, ReservationRepository reservationRepository){
         this.userRepo = userRepo;
         this.flightRepository=flightRepository;
         this.reservationMapper = reservationMapper;
         this.reservationRepository= reservationRepository;
    }


    @Override
    public Reservation makeReservation(User user, String flightNumber)
    {

        Reservation reservationDTO= new Reservation();

        Optional<Flight> flight = flightRepository.findByFlightNumber(flightNumber);
        Flight f = flight.get();
        
        if(flight.isEmpty()){
            throw new EntityNotFoundException("No such flight");
        }

        reservationDTO.setFlight(flight.get());
        reservationDTO.setUser(user);
        reservationDTO.setStatus(StatusEnum.RESERVED);


        f.setAvailableSeats(flight.get().getAvailableSeats()-1);
        flightRepository.save(f);

        reservationRepository.save(reservationDTO);
       
        
        return reservationDTO;
        
    }



    @Override
    public List<Flight> getrReservations(User user){

        List<Long> ids = reservationRepository.getFlightIds(user.getId());

        List<Flight> flights = flightRepository.getFlights(ids);

        return flights;
    }
}
