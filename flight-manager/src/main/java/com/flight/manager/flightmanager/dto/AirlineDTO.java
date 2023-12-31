package com.flight.manager.flightmanager.dto;

public class AirlineDTO {
    private Long id;
    private String name;
    private String country;

    // Constructors, getters, and setters

    public AirlineDTO() {
        // Default constructor
    }

    public AirlineDTO(Long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    // Getters and Setters for fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
