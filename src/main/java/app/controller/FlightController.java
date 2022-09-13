package app.controller;

import app.data.entity.Flight;
import app.service.FlightService;

import java.util.List;

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
    public Flight getFlight(int id){
        return flightService.getFlight(id);
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
}
