package com.flight.manager.flightmanager.dto;

import com.flight.manager.flightmanager.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponceDTO {
    private String token;
    private User user;
}
