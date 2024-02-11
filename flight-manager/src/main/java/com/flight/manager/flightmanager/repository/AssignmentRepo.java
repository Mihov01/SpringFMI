package com.flight.manager.flightmanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flight.manager.flightmanager.model.CrewAssignment;

public interface AssignmentRepo extends JpaRepository<CrewAssignment, Long> {
    

    @Query(value = "Select flight_id from crew_assignment where crew_member_id = :id order by flight_id asc", nativeQuery = true)
    List<Long> getFlightIds(@Param("id") Long id);

    @Query(value = "Select * from crew_assignment where crew_member_id = :id order by flight_id asc", nativeQuery = true)
    List<CrewAssignment> getAssignments(@Param("id") Long id);


    
    @Query(value = "Select * from crew_assignment where crew_member_id = :id and flight_id = :idf order by flight_id asc limit 1", nativeQuery = true)
    Optional<CrewAssignment> getAssignment(@Param("id") Long id , @Param("idf") Long idf);
}
