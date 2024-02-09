package com.flight.manager.flightmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flight.manager.flightmanager.model.Flight;
import com.flight.manager.flightmanager.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    @Query(value = "Select flight_id from reservation where user_id = :id", nativeQuery = true)
    List<Long> getFlightIds(@Param("id") Long id);
    
}
