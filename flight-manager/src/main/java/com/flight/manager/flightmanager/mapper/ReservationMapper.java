package com.flight.manager.flightmanager.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.flight.manager.flightmanager.dto.FlightDTO;
import com.flight.manager.flightmanager.dto.ReservationDTO;
import com.flight.manager.flightmanager.model.Flight;
import com.flight.manager.flightmanager.model.Reservation;
import com.flight.manager.flightmanager.model.User;

@Component
public class ReservationMapper {
        
    private final ModelMapper modelMapper;


    public ReservationMapper(ModelMapper modelMapper) {
            this.modelMapper = modelMapper;
    }

    public ReservationDTO toReservationDto(Reservation reservation) {
        return modelMapper.map(reservation, ReservationDTO.class);
    }

    public Reservation toReservation(ReservationDTO reservationDTO) {
        return modelMapper.map(reservationDTO, Reservation.class);
    }

}
