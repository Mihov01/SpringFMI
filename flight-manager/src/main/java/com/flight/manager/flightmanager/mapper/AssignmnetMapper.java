package com.flight.manager.flightmanager.mapper;

import org.springframework.stereotype.Component;

import com.flight.manager.flightmanager.dto.AssignmentDTO;
import com.flight.manager.flightmanager.model.CrewAssignment;
import com.flight.manager.flightmanager.model.Flight;
import com.flight.manager.flightmanager.model.User;

@Component
public class AssignmnetMapper {
    
    public AssignmentDTO toDto(CrewAssignment assignment , Flight flight, User user){
        AssignmentDTO dto = new AssignmentDTO();
        dto.setId(assignment.getId());
        dto.setArrivalTime(flight.getArrivalTime());
        dto.setDepartureTime(flight.getDepartureTime());
        dto.setFlightNumber(flight.getFlightNumber());
        dto.setRole(assignment.getRole());
        dto.setSourceAirportCode(flight.getSourceAirportCode());
        dto.setDestinationAirportCode(flight.getDestinationAirportCode());
        dto.setUserName(user.getUsername());
        return dto;
    }
}
