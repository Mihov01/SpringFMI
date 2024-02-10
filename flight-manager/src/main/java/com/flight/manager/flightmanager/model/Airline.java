package com.flight.manager.flightmanager.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "airline")
@Data
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "country", nullable = false)
    private String country;

    @OneToMany(mappedBy = "airline" , fetch = FetchType.EAGER)
    private List<Flight> flights;

}
