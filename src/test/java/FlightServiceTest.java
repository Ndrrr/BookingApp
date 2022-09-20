import app.data.dao.FlightDao;
import app.data.entity.Airport;
import app.data.entity.Flight;
import app.data.entity.Passenger;
import app.service.FlightService;
import app.util.FlightGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightServiceTest {
    private FlightService flightService;
    private final FlightGenerator flightGenerator = new FlightGenerator();


    @BeforeEach
    void setUp() {
        flightService = new FlightService(new FlightDao());
        flightService.addFlight(flightGenerator.generateFlights(50));
    }

    @Test
    void testAddFlight(){
        int expected = flightService.getFlights().size() + 1;
        Flight flight = flightGenerator.generateFlight();
        flightService.addFlight(flight);
        int result = flightService.getFlights().size();
        assertEquals(expected, result);
    }
    @Test
    void testRemoveFlight(){
        int id = flightService.getFlights().get(0).getId();
        flightService.removeFlight(id);
        int expected = 49;
        int result = flightService.getFlights().size();
        assertEquals(expected, result);
    }

    @Test
    void testRemoveFakeFlight(){
        List<Flight> list = flightService.getFlights();
        int[] id = {92838323};
        while(list.stream().anyMatch(flight -> flight.getId() == id[0])){
            id[0]++;
            System.out.println(id[0]);
        }
        flightService.removeFlight(id[0]);
        int expected = 50;
        int result = flightService.getFlights().size();
        assertEquals(expected, result);
    }

    @Test
    void testUpdateFlight(){
        Flight flight = flightService.getFlights().get(0);
        flight.setAirportTo(Airport.ATL);
        flightService.updateFlight(0, flight);
        String expected = "Atlanta";
        String result = flightService.getFlights().get(0).getAirportTo().getCity();
        assertEquals(expected, result);
    }

    @Test
    void testAddPassengerToFlight(){
        Flight flight = flightService.getFlights().get(0);
        int expected = flight.getPassengerList().size() + 1;
        flightService.addPassengerToFlight(flight, List.of(new Passenger("John", "Doe")));
        int result = flightService.getFlights().get(0).getPassengerList().size();
        assertEquals(expected, result);
    }

}
