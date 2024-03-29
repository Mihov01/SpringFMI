package com.flight.manager.flightmanager.model;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "airport")
@Data
public class Airport {

    @Id
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "facilities")
    private String facilities;

    @OneToMany(mappedBy = "sourceAirportCode")
    private List<Flight> departingFlights;

    @OneToMany(mappedBy = "destinationAirportCode")
    private List<Flight> arrivingFlights;
}
