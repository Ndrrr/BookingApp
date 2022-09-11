import entity.Airline;
import entity.Airport;
import entity.Flight;
import entity.User;
import util.FlightGenerator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = new FlightGenerator().generateFlights(100);
        flights.sort((f1, f2) -> ((Integer)f1.getId()).compareTo(f2.getId()));
        System.out.println(flights);
    }
}
