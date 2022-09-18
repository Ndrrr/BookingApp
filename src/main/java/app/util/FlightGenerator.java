package app.util;

import app.data.entity.Airline;
import app.data.entity.Airport;
import app.data.entity.Flight;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class FlightGenerator {
    private final Random rand = new Random();
    public Flight generateFlight(){
        Airline airline = Airline.values()[rand.nextInt(Airline.values().length)];
        Airport from = Airport.values()[rand.nextInt(Airport.values().length)];
        Airport to = Airport.values()[rand.nextInt(Airport.values().length)];
        while (from.equals(to)){
            to = Airport.values()[rand.nextInt(Airport.values().length)];
        }
        String flightDesignation = airline.name() + rand.nextInt(100,1000);
        LocalDateTime dateTime = LocalDateTime.now().plusDays(rand.nextInt(30)).plusHours(rand.nextInt(24)).plusMinutes(rand.nextInt(60));
        Duration duration = Duration.ofHours(rand.nextInt(10));
        int seats = rand.nextInt(100);
        return new Flight(flightDesignation, from, to, airline, dateTime, duration, seats);
    }

    public List<Flight> generateFlights(int count){
        HashSet flights = new HashSet();
        while (flights.size() < count){
            flights.add(generateFlight());
        }
        return new ArrayList<>(flights);
    }

}
