package app;

import app.command.LoginCommand;
import app.command.RegisterCommand;
import app.ui.console.Console;
import app.ui.console.ProdConsole;
import app.controller.FlightController;
import app.data.entity.Flight;
import app.ui.menu.Menu;
import app.ui.menu.item.*;
import app.util.FlightGenerator;

import java.util.List;

public class Main {
    static Context context = Context.getInstance();

    public static void main(String[] args) {
        initFlights(100);
        Console console = new ProdConsole();
        Menu menu = new Menu(console,
                new ExitMenuItem(0, console),
                new RegisterMenuItem(1, console,new RegisterCommand()),
                new LoginMenuItem(2, console,new LoginCommand()),
                new TimeTableMenuItem(3, console, (FlightController) context.get("flightController"))
               );
        while(true){
            menu.display();
        }
    }

    public static void initFlights(int count) {
        List<Flight> flights = new FlightGenerator().generateFlights(count);
        FlightController flightController = (FlightController) context.get("flightController");
        flightController.getFlights().addAll(flights);
    }
}
