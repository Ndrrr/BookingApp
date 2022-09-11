package dao;

import entity.Entity;
import entity.Flight;
import util.FlightGenerator;

import java.util.ArrayList;
import java.util.List;

public class FlightDao implements Dao<Flight>{
    private static final String path = "flights.txt";
    List<Flight> flightList;

    public FlightDao(){
        flightList = new ArrayList<>();
    }
    public FlightDao(List<Flight> flightList){
        this.flightList = flightList;
    }

    @Override
    public List<Flight> get() {
        return flightList;
    }

    @Override
    public void save() {
        Db.save(flightList, path);
    }

    @Override
    public void load() {
        List<Entity> list = Db.load(path).orElse(new ArrayList<Entity>());
        boolean match = list.stream()
                .allMatch(e -> e instanceof Flight);
        if(match){
            flightList = new ArrayList<>(
                    list.stream()
                    .map(e -> (Flight) e)
                    .toList());
        }
    }
}
