package com.flight.manager.flightmanager.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "flight")
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "flight_number", nullable = false, unique = true)
    private String flightNumber;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    @Column(name = "source_airport_code", nullable = false)
    private String sourceAirportCode;

    @Column(name = "destination_airport_code", nullable = false)
    private String destinationAirportCode;

    @Column(name = "available_seats", nullable = false)
    private int availableSeats;

    @ManyToOne
    @JoinColumn(name = "airline_id", nullable = false)
    private Airline airline;




    // Other necessary fields and relationships

    // Getters and setters
}