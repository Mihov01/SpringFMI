package com.flight.manager.flightmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flight.manager.flightmanager.model.CrewAssignment;

public interface AssignmentRepo extends JpaRepository<CrewAssignment, Long> {
    

    @Query(value = "Select flight_id from crew_assignment where crew_member_id = :id order by flight_id asc", nativeQuery = true)
    List<Long> getFlightIds(@Param("id") Long id);

    @Query(value = "Select * from crew_assignment where crew_member_id = :id order by flight_id asc", nativeQuery = true)
    List<CrewAssignment> getAssignments(@Param("id") Long id);
}
