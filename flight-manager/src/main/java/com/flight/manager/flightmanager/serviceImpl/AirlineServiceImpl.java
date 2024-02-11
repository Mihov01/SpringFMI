package com.flight.manager.flightmanager.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flight.manager.flightmanager.dto.AirlineDTO;
import com.flight.manager.flightmanager.mapper.AirlineMapper;
import com.flight.manager.flightmanager.model.Airline;
import com.flight.manager.flightmanager.repository.AirlineRepo;
import com.flight.manager.flightmanager.service.AirlineService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AirlineServiceImpl implements AirlineService {
    
    private final AirlineRepo airlineRepo;
    private final AirlineMapper airlineMapper;

    AirlineServiceImpl(AirlineRepo airlineRepo, AirlineMapper airlineMapper) {
        this.airlineRepo = airlineRepo;
        this.airlineMapper = airlineMapper;
    }

    @Override
    public List<AirlineDTO> getAllAirlines() {
        List<Airline> airlines = airlineRepo.findAll();
        if (airlines.isEmpty()) {
            throw new EntityNotFoundException("No such Airline");
        }
        return airlineMapper.mapEntitiesToDTOs(airlines);
    }

    @Override
    public AirlineDTO createAirline(AirlineDTO airlineDTO) {
        Airline entity = airlineMapper.toAirlineEntity(airlineDTO);
        Airline savedEntity = airlineRepo.save(entity);
        return airlineMapper.toAirlineDTO(savedEntity);
    }

    @Override
    public AirlineDTO getById(Long id) {
        Optional<Airline> optionalEntity = airlineRepo.findById(id);
        if (optionalEntity.isEmpty()) {
            throw new EntityNotFoundException("No such Airline");
        }
        return optionalEntity.map(airlineMapper::toAirlineDTO).orElse(null);
    }

    @Override
    public AirlineDTO getByName(String name) {
        Optional<Airline> optionalEntity = airlineRepo.getAirlineByName(name);
        if (optionalEntity.isEmpty()) {
            throw new EntityNotFoundException("No such Airline");
        }
        return optionalEntity.map(airlineMapper::toAirlineDTO).orElse(null);
    }

    @Override
    public AirlineDTO updateAirline(AirlineDTO airlineDTO) {
        Optional<Airline> optionalEntity = airlineRepo.getAirlineByName(airlineDTO.getName());
        if (optionalEntity.isEmpty()) {
            throw new EntityNotFoundException("No such Airline");
        }
        Airline existingAirline = optionalEntity.get();
        existingAirline.setName(airlineDTO.getName());
        existingAirline.setCountry(airlineDTO.getCountry());
        Airline updatedEntity = airlineRepo.save(existingAirline);
        return airlineMapper.toAirlineDTO(updatedEntity);
    }

    @Override
    public AirlineDTO deleteAirline(Long id) {
        Optional<Airline> optionalEntity = airlineRepo.findById(id);
        if (optionalEntity.isEmpty()) {
            throw new EntityNotFoundException("No such Airline");
        }
        Airline deletedEntity = optionalEntity.get();
        airlineRepo.deleteById(id);
        return airlineMapper.toAirlineDTO(deletedEntity);
    }
}
