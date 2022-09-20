package app.ui.menu.item;

import app.ui.menu.command.EmptyCommand;
import app.ui.console.Console;
import app.controller.FlightController;
import app.data.entity.Flight;

import java.util.List;
import java.util.Map;

public class TimeTableMenuItem extends MenuItem {
    private Console console;
    private FlightController flightController;
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
    protected MenuItemStatus response(Map <String, String> data) {
        List<Flight> flights = flightController.getFlights().stream().sorted().toList();
        console.println(console.lineSeparator());
        flights.forEach(flight -> console.println(flight.prettyFormat()));
        console.waitForEnter();
        return MenuItemStatus.NEXT;
    }
}
