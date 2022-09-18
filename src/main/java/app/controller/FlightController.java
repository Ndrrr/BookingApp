package app.controller;

import app.data.entity.Flight;
import app.data.entity.Passenger;
import app.service.FlightService;

import java.util.List;
import java.util.Optional;

public class FlightController {
    private FlightService flightService;

    public FlightController(FlightService flightService){
        this.flightService = flightService;
    }

    public void addFlight(Flight flight){
        flightService.addFlight(flight);
    }
    public void removeFlight(Flight flight){
        flightService.removeFlight(flight);
    }
    public void removeFlight(int id){
        flightService.removeFlight(id);
    }
    public void updateFlight(int id, Flight flight){
        flightService.updateFlight(id, flight);
    }
    public Optional<Flight> getFlight(int id){
        return flightService.getFlight(id);
    }
    public Optional<Flight> getFlightByDesignation(String designation){
        return getFlights().stream()
                .filter(flight -> flight.getFlightDesignation().equals(designation))
                .findFirst();
    }
    public List<Flight> getFlights(){
        return flightService.getFlights();
    }
    public void saveToDb(){
        flightService.saveToDb();
    }
    public void loadFromDb(){
        flightService.loadFromDb();
    }
    public void addPassengerToFlight(Flight flight, List<Passenger> passengerList){
        flightService.addPassengerToFlight(flight, passengerList);
    }
}
