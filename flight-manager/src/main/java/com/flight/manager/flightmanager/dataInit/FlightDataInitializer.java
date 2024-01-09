// package com.flight.manager.flightmanager.dataInit;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

// import com.flight.manager.flightmanager.model.Airline;
// import com.flight.manager.flightmanager.model.Flight;
// import com.flight.manager.flightmanager.repository.AirlineRepo;
// import com.flight.manager.flightmanager.repository.FlightRepository;

// import java.time.LocalDateTime;

// @Component
// public class FlightDataInitializer implements CommandLineRunner {

//     private final FlightRepository flightRepository;
//     private final AirlineRepo airlineRepository;

//     public FlightDataInitializer(FlightRepository flightRepository, AirlineRepo airlineRepository) {
//         this.flightRepository = flightRepository;
//         this.airlineRepository = airlineRepository;
//     }

//     @Override
//     public void run(String... args) {
//         String airlineName = "YourAirlineName";

//         // Check if the airline already exists
//         Airline existingAirline = airlineRepository.findByName(airlineName);
//         if (existingAirline == null) {
//             // Create a new airline
//             Airline airline = new Airline();
//             airline.setName(airlineName);
//             airline.setCountry("CountryName");
//             airlineRepository.save(airline);

//             // Create flights associated with the airline
//             Flight flight1 = new Flight();
//             flight1.setFlightNumber("FL123");
//             // Set other flight properties
//             flight1.setAirline(airline);

//             Flight flight2 = new Flight();
//             flight2.setFlightNumber("FL456");
//             // Set other flight properties
//             flight2.setAirline(airline);

//             // Save flights
//             flightRepository.save(flight1);
//             flightRepository.save(flight2);
//         } else {
//             // Create flights associated with the airline
//             Flight flight1 = new Flight();
//             flight1.setFlightNumber("FL123");
//             // Set other flight properties
//             flight1.setAirline(existingAirline);

//             Flight flight2 = new Flight();
//             flight2.setFlightNumber("FL456");
//             // Set other flight properties
//             flight2.setAirline(existingAirline);

//             flight1.setArrivalTime(LocalDateTime.now());
//             flight2.setArrivalTime(LocalDateTime.now());
//             flight1.setDepartureTime(LocalDateTime.now());
//             flight2.setDepartureTime(LocalDateTime.now());

//             // Save flights
//             flightRepository.save(flight1);
//             flightRepository.save(flight2);
//         }
//     }
// }
