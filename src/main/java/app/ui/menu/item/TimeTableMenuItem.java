package app.ui.menu.item;

import app.command.EmptyCommand;
import app.ui.console.Console;
import app.controller.FlightController;
import app.data.entity.Flight;

import java.util.List;
import java.util.Map;

public class TimeTableMenuItem extends MenuItem {
    private Console console;
    FlightController flightController;
    public TimeTableMenuItem(int id, Console console, FlightController flightController){
        super(id, new EmptyCommand());
        this.flightController = flightController;
        this.console = console;
    }
    @Override
    public String getDescription() {
        return "View Timetable";
    }
    @Override
    protected Map<String, String> getInput() {
        return null;
    }

    @Override
    protected MenuItemStatus response() {
        List<Flight> flights = flightController.getFlights();
        flights.forEach(flight -> console.println(flight.prettyFormat()));
        return MenuItemStatus.CONTINUE;
    }
}
