package com.flight.manager.flightmanager.dto;

import java.time.LocalDateTime;

import com.flight.manager.flightmanager.model.CrewRole;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentDTO {


    Long id;

    private CrewRole role; 

    
 
    private String flightNumber;


    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;


    private String sourceAirportCode;

    private String destinationAirportCode;

    private String userName;

    
    
}
