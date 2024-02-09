package com.flight.manager.flightmanager.dto;

import java.util.List;

import com.flight.manager.flightmanager.model.Flight;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirportDTO {
    


    private String code;

    private String name;


    private String location;


    private String facilities;

    private List<Flight> departingFlights;

  
    private List<Flight> arrivingFlights;
}
