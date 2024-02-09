package com.flight.manager.flightmanager.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.flight.manager.flightmanager.model.Airline;
import com.flight.manager.flightmanager.model.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    
    Optional<Airport> findByName(String name);

    Optional<Airport> findByCode(String name);

    Airport deleteByCode(String code);
}
