package com.flight.manager.flightmanager.service;

import java.util.List;

import com.flight.manager.flightmanager.dto.AssignmentDTO;
import com.flight.manager.flightmanager.model.User;

public interface AssignmnetService {
    

    List<AssignmentDTO> getAssinments(User user);
    List<AssignmentDTO> getAllAssinments();
}
