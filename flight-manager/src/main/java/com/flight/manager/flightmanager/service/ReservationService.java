package com.flight.manager.flightmanager.service;

import java.util.List;

import com.flight.manager.flightmanager.dto.FlightDTO;
import com.flight.manager.flightmanager.dto.ReservationDTO;
import com.flight.manager.flightmanager.model.Flight;
import com.flight.manager.flightmanager.model.Reservation;
import com.flight.manager.flightmanager.model.User;

public interface ReservationService{


    Reservation makeReservation(User user, String flightNumber);
    List<Flight> getrReservations(User user);

    
}