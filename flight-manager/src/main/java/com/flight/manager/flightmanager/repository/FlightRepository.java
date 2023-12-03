package com.flight.manager.flightmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flight.manager.flightmanager.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {
    
}
