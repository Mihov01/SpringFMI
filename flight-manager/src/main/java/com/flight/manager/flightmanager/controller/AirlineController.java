package com.flight.manager.flightmanager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.flight.manager.flightmanager.dto.AirlineDTO;
import com.flight.manager.flightmanager.dto.FlightDTO;
import com.flight.manager.flightmanager.service.AirlineService;
import com.flight.manager.flightmanager.service.FlightService;
@RestController
@RequestMapping(value = "/flight_manager")
public class AirlineController {
    
    private final AirlineService airlineService;
    private final FlightService flightService;


    AirlineController(AirlineService airlineService , FlightService flightService)
    {
        this.airlineService = airlineService;
        this.flightService = flightService;
    }

    @GetMapping
    ResponseEntity<List<AirlineDTO>> getAirlines()
    {
        return ResponseEntity.ok().body(airlineService.getAllAirlines());
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<AirlineDTO> getbyId(@PathVariable Long id)
    {
      AirlineDTO  result = airlineService.getById(id);
      return ResponseEntity.ok(result);
    }

      @GetMapping(value = "/name/{name}")
    ResponseEntity<AirlineDTO> getbyName(@PathVariable String name)
    {
      AirlineDTO  result = airlineService.getByName(name);
      return ResponseEntity.ok(result);
    }

    @PostMapping
    ResponseEntity<AirlineDTO> createAirline(@RequestBody AirlineDTO airline)
    {
     AirlineDTO created = airlineService.createAirline(airline);
     return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}").build(created.getId())).body(created);
    }

    @PutMapping
    ResponseEntity<AirlineDTO> updateAirline(@RequestBody AirlineDTO airline)
    {
    
        AirlineDTO updated = airlineService.updateAirline(airline);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<AirlineDTO> deleteAirline(@PathVariable Long id)
    {
        AirlineDTO deleted = airlineService.deleteAirline(id);

        return ResponseEntity.ok(deleted);
    }


    @GetMapping(value = "/flights")
    List<FlightDTO> getFlights()
    {
      return flightService.getAllFlights();
    }

    @PostMapping(value = "/flights/add")
    ResponseEntity<FlightDTO> addFlight(@RequestBody FlightDTO flightDTO)
    {
      System.out.println(flightDTO.getAvailableSeats());
      FlightDTO created =  flightService.addFlight(flightDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}").build(created.getId())).body(created);
    }


    @PutMapping(value = "/flights/update")
    FlightDTO updateFlight (@RequestBody FlightDTO flightDTO){
      return flightService.updateFlight(flightDTO);
    }

    @GetMapping(value = "/flights/{id}")
    FlightDTO getById(@PathVariable Long id){
      return flightService.getById(id);
    }

    @DeleteMapping(value = "flights/delete/{id}")
    FlightDTO deleteById(@PathVariable Long id){
      return flightService.deleteById(id);
    }


}
