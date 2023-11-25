package com.flight.manager.flightmanager.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flight.manager.flightmanager.dto.AirlineDTO;
import com.flight.manager.flightmanager.mapper.AirlineMapper;
import com.flight.manager.flightmanager.model.Airline;
import com.flight.manager.flightmanager.repository.AirlineRepo;
import com.flight.manager.flightmanager.service.AirlineService;

@Service
public class AirlineServiceImpl implements AirlineService {
    
    private final AirlineRepo airlineRepo;
    private final AirlineMapper airlineMapper;



    AirlineServiceImpl(AirlineRepo airlineRepo , AirlineMapper  airlineMapper){

        this.airlineRepo =airlineRepo;
        this.airlineMapper = airlineMapper;
    }


    @Override
    public List<AirlineDTO> getAllAirlines()
    {
        List<Airline> airlines = airlineRepo.findAll();

        if(airlines.isEmpty())
        {
            return null;
        }
        else 
        {
            return airlineMapper.mapEntitiesToDTOs(airlines);
        }
    }

    @Override
    public AirlineDTO createAirline(AirlineDTO airlineDTO)
    {
        Airline entity = airlineMapper.toAirlineEntity(airlineDTO);

        Airline e = airlineRepo.save(entity);

        return airlineMapper.toAirlineDTO(e);
    }

    @Override
    public   AirlineDTO getById(Long id)
    {
        Optional<Airline> entity = airlineRepo.getAirlineById(id);

        if(entity.isEmpty())
        {
           // TODO : throw exception
        }

        return airlineMapper.toAirlineDTO(entity.get());
        
    }

    @Override
    public   AirlineDTO getByName(String name)
    {
        Optional<Airline> entity = airlineRepo.getAirlineByName(name);

        if(entity.isEmpty())
        {
           // TODO : throw exception
        }

        return airlineMapper.toAirlineDTO(entity.get());
        
    }

    @Override
    public AirlineDTO updateAirline(AirlineDTO airline)
    {
        

        Optional<Airline> entity = airlineRepo.getAirlineByName(airline.getName());

        if(entity.isEmpty())
        {
            //TODO throw exception;
        }


        Airline toUpdate = entity.get();
        toUpdate.setName(airline.getName());
        toUpdate.setCountry(airline.getCountry());
        Airline updated = airlineRepo.save(toUpdate);

        return airlineMapper.toAirlineDTO(updated);

    }

    @Override 
   public  AirlineDTO deleteAirline(Long id)
   {

    Optional<Airline> toDelete = airlineRepo.getAirlineById(id);

    if(toDelete.isEmpty()){
        // TODO throw exception 
    }


    airlineRepo.deleteById(id);

    return airlineMapper.toAirlineDTO(toDelete.get());
   }
}
