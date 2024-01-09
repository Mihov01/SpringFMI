package com.flight.manager.flightmanager.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.flight.manager.flightmanager.model.Airline;

@Repository
public interface AirlineRepo extends JpaRepository<Airline, Long> {
    
    @Query(value = "SELECT a FROM Airline a WHERE a.name = :name")
    Optional<Airline> getAirlineByName(@Param("name") String name);

    @Query(value = "SELECT a FROM Airline a WHERE a.id = :id")
    Optional<Airline> getAirlineById(@Param("id") Long id);


    
    Airline findByName(String name);
}
