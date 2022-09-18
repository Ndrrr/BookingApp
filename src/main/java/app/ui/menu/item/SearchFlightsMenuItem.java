package app.ui.menu.item;

import app.Context;
import app.command.SearchFlightsCommand;
import app.controller.FlightController;
import app.data.entity.Flight;
import app.ui.console.Console;
import app.util.InputUtil;

import java.util.*;

public class SearchFlightsMenuItem extends MenuItem {
    private Console console;
    private Context context = Context.getInstance();
    private FlightController flightController;
    public SearchFlightsMenuItem(int id, Console console, FlightController flightController) {
        super(id, new SearchFlightsCommand());
        this.console = console;
        this.flightController = flightController;
    }

    @Override
    public String getDescription() {
        return "Search Flights";
    }

    @Override
    protected Map<String, String> getInput() {
        Map<String, String> data = new HashMap<>(InputUtil.getFlightData());
        return data;
    }

    @Override
    protected MenuItemStatus response(Map<String, String> data) {
        String errorMsg = (String) context.get("errorMsg");
        if(errorMsg != null){
            console.println(errorMsg);
        }
        else{
            String flights = data.get("flights");
            if(flights != null && !flights.isEmpty()){
                console.println("Available flights:");
                List<Flight> flightList = InputUtil.deserializeFlights(flights, flightController);
                flightList.forEach(flight -> console.println(flight.prettyFormat()));
            } else {
                console.println("No flights found");
            }
        }
        return MenuItemStatus.NEXT;
    }

}
