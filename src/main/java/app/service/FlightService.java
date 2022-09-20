package app.service;

import app.data.dao.FlightDao;
import app.data.entity.Flight;
import app.data.entity.Passenger;

import java.util.List;
import java.util.Optional;

public class FlightService {
    private final FlightDao flightDao;
    public FlightService(FlightDao flightDao){
        this.flightDao = flightDao;
    }

    public void addFlight(Flight flight){
        flightDao.add(flight);
    }
    public void addFlight(List<Flight> flights){
        flightDao.addAll(flights);
    }
    public void removeFlight(Flight flight){
        flightDao.delete(flight);
    }
    public void removeFlight(int id){
        flightDao.delete(id);
    }
    public void updateFlight(int id, Flight flight){
        flightDao.update(id, flight);
    }
    public Optional<Flight> getFlight(int id){
        return flightDao.get(id);
    }
    public Optional<Flight> getFlightByDesignation(String designation){

        return flightDao.getFlightByDesignation(designation);
    }
    public List<Flight> getFlights(){
        return flightDao.get();
    }
    public void saveToDb(){
        flightDao.save();
    }
    public void loadFromDb(){
        flightDao.load();
    }
    public void addPassengerToFlight(Flight flight, List<Passenger> passengerList){
        flight.getPassengerList().addAll(passengerList);
        updateFlight(flight.getId(), flight);
    }



}
