package com.flight.manager.flightmanager.serviceImpl;

import java.util.List;
import java.util.Optional;

import com.flight.manager.flightmanager.dto.AirportDTO;
import com.flight.manager.flightmanager.mapper.AirportMapper;
import com.flight.manager.flightmanager.model.Airport;
import com.flight.manager.flightmanager.repository.AirportRepository;
import com.flight.manager.flightmanager.service.AirportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;

    @Autowired
    public AirportServiceImpl(AirportRepository airportRepository, AirportMapper airportMapper) {
        this.airportRepository = airportRepository;
        this.airportMapper = airportMapper;
    }

    @Override
    public List<AirportDTO> getAllAirports() {
        List<Airport> airports = airportRepository.findAll();
        return airportMapper.mapEntitiesToDTOs(airports);
    }

    @Override
    public AirportDTO createAirport(AirportDTO airportDTO) {
        Airport airport = airportMapper.toAirportEntity(airportDTO);
        Airport savedAirport = airportRepository.save(airport);
        return airportMapper.toAirportDTO(savedAirport);
    }

    @Override
    public AirportDTO getByCode(String code) {
        Optional<Airport> optionalAirport = airportRepository.findByCode(code);
        return optionalAirport.map(airportMapper::toAirportDTO).orElse(null);
    }

    @Override
    public AirportDTO getByName(String name) {
        Optional<Airport> optionalAirport = airportRepository.findByName(name);
        return optionalAirport.map(airportMapper::toAirportDTO).orElse(null);
    }

    @Override
    public AirportDTO updateAirport(AirportDTO airportDTO) {
        Optional<Airport> optionalAirport = airportRepository.findByCode(airportDTO.getCode());
        if (optionalAirport.isPresent()) {
            Airport updatedAirport = airportRepository.save(airportMapper.toAirportEntity(airportDTO));
            return airportMapper.toAirportDTO(updatedAirport);
        }
        return null; // Handle not found scenario
    }

    @Override
    public AirportDTO deleteAirport(String code) {
        Optional<Airport> optionalAirport = airportRepository.findByCode(code);
        if (optionalAirport.isPresent()) {
            airportRepository.deleteByCode(code);
            return airportMapper.toAirportDTO(optionalAirport.get());
        }
        return null; // Handle not found scenario
    }
}
