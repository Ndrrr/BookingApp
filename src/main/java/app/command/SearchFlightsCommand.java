package app.command;

import app.Context;
import app.controller.FlightController;
import app.data.entity.Airport;
import app.data.entity.Flight;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SearchFlightsCommand implements Command{
    private final Context context = Context.getInstance();
    private final FlightController flightController = (FlightController) context.get("flightController");
    @Override
    public void execute(Map<String, String> data) {
        showFilteredFlights(data);
    }
    private void showFilteredFlights(Map< String, String > data) {
        String origin = data.get("origin");
        String destination = data.get("destination");
        String date = data.get("date");

        int numberOfPassengers = Integer.parseInt(data.get("passengerCount"));
        List<Flight> flights = filterFlight(origin, destination, date, numberOfPassengers);
        if(flights!=null)
            data.put("flights", flights.stream().map(Flight::getId).map(String::valueOf).collect(Collectors.joining(",")));
    }

    private List<Flight> filterFlight(String from, String to, String dateStr, int numberOfPassengers){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        Optional<Airport> fromAirportOptional = Arrays.stream(Airport.values()).filter(airport -> airport.getCity().equalsIgnoreCase(from)).findFirst();
        Optional<Airport> toAirportOptional = Arrays.stream(Airport.values()).filter(airport -> airport.getCity().equalsIgnoreCase(to)).findFirst();
        if(fromAirportOptional.isEmpty()) {
            context.put("errorMsg", "We couldn't find desired flight to given origin");
            return null;
        }
        if(toAirportOptional.isEmpty()) {
            context.put("errorMsg", "We couldn't find desired flight to given destination");
            return null;
        }
        Airport fromAirport = fromAirportOptional.get();
        Airport toAirport = toAirportOptional.get();

        LocalDate date = LocalDate.parse(dateStr, dtf);
        return flightController
                .getFlights()
                .stream()
                .filter(flight -> flight.getAirportTo().equals(toAirport) &&
                        flight.getAirportFrom().equals(fromAirport) &&
                        flight.getDateTime().toLocalDate().equals(date) &&
                        flight.getAvailableSeats() >= numberOfPassengers)
                .sorted()
                .collect(Collectors.toList());
    }

}
