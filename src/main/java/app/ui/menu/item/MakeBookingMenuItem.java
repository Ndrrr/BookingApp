package app.ui.menu.item;

import app.Context;
import app.command.Command;
import app.command.MakeBookingCommand;
import app.command.SearchFlightsCommand;
import app.controller.FlightController;
import app.data.entity.Airport;
import app.data.entity.Flight;
import app.ui.console.Console;
import app.util.InputUtil;

import java.util.*;

public class MakeBookingMenuItem extends MenuItem {
    private final Console console;
    private final Context context = Context.getInstance();
    private final FlightController flightController;


    public MakeBookingMenuItem(int id, Console console, FlightController flightController) {
        super(id, new MakeBookingCommand());
        this.console = console;
        this.flightController = flightController;
    }

    @Override
    public String getDescription() {
        return "Make Booking";
    }

    @Override
    protected Map<String, String> getInput() {
        Map<String, String> data = new HashMap<>();
        data.put("username", (String) context.get("currentUser"));
        data.putAll(InputUtil.getFlightData());

        Command showFilteredFlights = new SearchFlightsCommand();
        showFilteredFlights.execute(data);
        String flights = data.get("flights");

        if(flights != null && !flights.isEmpty()){
            int passengerCount = data.get("passengerCount") != null ? Integer.parseInt(data.get("passengerCount")) : 0;
            console.println("Available flights:");
            List<Flight> flightList = InputUtil.deserializeFlights(flights, flightController);
            flightList.forEach(flight -> console.println(flight.prettyFormat()));


            for(int passengerId = 0; passengerId < passengerCount; passengerId++){
                console.print("Enter passenger name: ");
                data.put("pName" + passengerId,console.nextLine());
                console.print("Enter passenger surname: ");
                data.put("pSurname" + passengerId, console.nextLine());
            }
            console.print("Enter Flight Designation: ");
            data.put("flightDesignation", InputUtil.validateDesignation(flightController));
        }else{
            context.put("errorMsg", "No flights found with given parameters");
        }

        return data;
    }
    @Override
    protected MenuItemStatus response(Map <String, String> data) {
        String errorMsg = context.getErrorMessage();

        console.println(console.lineSeparator());
        console.println(Objects.requireNonNullElse(errorMsg, "Booking is done successfully"));
        console.waitForEnter();
        return MenuItemStatus.NEXT;
    }


}
