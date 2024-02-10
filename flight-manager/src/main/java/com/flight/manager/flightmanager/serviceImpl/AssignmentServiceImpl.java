package com.flight.manager.flightmanager.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.flight.manager.flightmanager.dto.AssignmentDTO;
import com.flight.manager.flightmanager.mapper.AssignmnetMapper;
import com.flight.manager.flightmanager.model.CrewAssignment;
import com.flight.manager.flightmanager.model.Flight;
import com.flight.manager.flightmanager.model.User;
import com.flight.manager.flightmanager.repository.AssignmentRepo;
import com.flight.manager.flightmanager.repository.FlightRepository;
import com.flight.manager.flightmanager.repository.UserRepo;
import com.flight.manager.flightmanager.service.AssignmnetService;

@Service
public class AssignmentServiceImpl implements AssignmnetService {
    
    private final FlightRepository flightRepository;
    private final AssignmentRepo assignmentRepo;
    private final AssignmnetMapper assignmnetMapper;
    private final UserRepo userRepo;


    AssignmentServiceImpl( FlightRepository flightRepository ,AssignmentRepo assignmentRepo,  AssignmnetMapper assignmnetMapper, UserRepo userRepo ){
        this.assignmentRepo=assignmentRepo;
        this.flightRepository = flightRepository;
        this.assignmnetMapper = assignmnetMapper;
        this.userRepo = userRepo;
    }


    @Override
    public List<AssignmentDTO> getAssinments(User user){

        List<Long> flight_ids = assignmentRepo.getFlightIds(user.getId());

        List<Flight> flights = flightRepository.getFlights(flight_ids);

        List<CrewAssignment> assignments = assignmentRepo.getAssignments(user.getId());

        List<AssignmentDTO> res = new ArrayList<>();
        int size = flights.size();
        for(int i =0 ; i < size; i++){

            AssignmentDTO dto = assignmnetMapper.toDto(assignments.get(i), flights.get(i), user);
            res.add(dto);
        }
        return res;
          
    }


    @Override
    public List<AssignmentDTO> getAllAssinments(){


        List<AssignmentDTO> res = new ArrayList<>();

      List<User> users= userRepo.findAll();

        
        for(User us : users){
            List<AssignmentDTO> res1 = new ArrayList<>();
            res1 = getAssinments(us);
            res.addAll(res1);
        }
        return res;
          
    }
}
