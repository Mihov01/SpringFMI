package com.flight.manager.flightmanager.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flight.manager.flightmanager.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> , JpaSpecificationExecutor<Flight>  {

    @Query(value = "Select * from flight where source_airport_code = :source and destination_airport_code = :destination and available_seats > 0", nativeQuery = true)
    List<Flight> getAllAvailableFlightsBetween(@Param("source") String source , @Param("destination") String destination );
    

    
    // Method to fetch flights by departure date
    List<Flight> findByDepartureTime(LocalDateTime departureTime);

    // Method to fetch flights by source airport code
    List<Flight> findBySourceAirportCode(String sourceAirportCode);

    // Method to fetch flights by departure date and source airport code
    List<Flight> findByDepartureTimeAndSourceAirportCode(LocalDateTime departureTime, String sourceAirportCode);

    Optional<Flight> findByFlightNumber(String flightNumber);

    @Query(value = "Select * from flight where id in :ids", nativeQuery = true)
    List<Flight> getFlights(@Param("ids") List<Long> ids);
}
