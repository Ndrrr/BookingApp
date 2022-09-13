package app.service;

import app.data.dao.FlightDao;
import app.data.entity.Flight;

import java.util.List;

public class FlightService {
    private final FlightDao flightDao;
    public FlightService(FlightDao flightDao){
        this.flightDao = flightDao;
    }

    public void addFlight(Flight flight){
        flightDao.add(flight);
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
    public Flight getFlight(int id){
        return flightDao.get(id).orElse(null);
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

}
