package com.flight.manager.flightmanager.dto;

import com.flight.manager.flightmanager.model.StatusEnum;

public class ReservationDTO {

    private Long id;
    private Long userId;
    private String flightNumber;
    private StatusEnum status;


    // Other necessary fields related to reservation details in the DTO

    // Constructors, getters, and setters

    public ReservationDTO() {
        // Default constructor
    }

    public ReservationDTO(Long id, Long userId, String flightNumber, String seatNumber, StatusEnum status) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.flightNumber=flightNumber;

    }

    // Getters and setters for each field

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFlightNumber(){
        return this.flightNumber;
    }

    
    public void setFlightNumber(String flightNumber){
         this.flightNumber  = flightNumber;
    }


    public StatusEnum getStatus(){
        return this.status;
    }

  
    public void setStatus(StatusEnum status){
         this.status = status;
    }


}
