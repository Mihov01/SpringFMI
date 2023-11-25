package com.flight.manager.flightmanager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "airline")
@Data
@RequiredArgsConstructor
@EqualsAndHashCode
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    Long id;

    @Column(name = "name" , nullable = false , unique = true)
    String name;

    @Column(name = "country", nullable = false)
    String country;
}
