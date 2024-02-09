package com.flight.manager.flightmanager.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FlightDTO {

    private Long id;
    private String flightNumber;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String sourceAirportCode;
    private String destinationAirportCode;
    private int availableSeats;
    private Integer price;

    // Constructors if needed

    // Getters and setters
}
