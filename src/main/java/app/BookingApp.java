package app;

import app.controller.FlightController;
import app.controller.UserController;
import app.data.entity.Flight;
import app.ui.console.Console;
import app.ui.console.ProdConsole;
import app.ui.menu.Menu;
import app.ui.menu.item.*;
import app.util.FlightGenerator;

import java.util.List;

public class BookingApp {
    private final Context context = Context.getInstance();
    public void run(){
        Console console = new ProdConsole();
        FlightController flightController = (FlightController) context.get("flightController");
        UserController userController = (UserController) context.get("userController");
        initFlights(100, flightController);

        Menu nonAuthenticatedMenu = new Menu(console,
                new ExitMenuItem(0, console),
                new RegisterMenuItem(1, console),
                new LoginMenuItem(2, console),
                new TimeTableMenuItem(3, console, flightController),
                new SearchFlightsMenuItem(4, console, flightController),
                new AutoSaveMenuItem(9, console)
        );
        Menu mainMenu = new Menu(console,
                new ExitMenuItem(0, console),
                new TimeTableMenuItem(1, console, flightController),
                new SearchFlightsMenuItem(2, console, flightController),
                new MakeBookingMenuItem(3, console, flightController),
                new ViewBookingMenuItem(4, console, userController),
                new CancelBookingMenuItem(5, console, userController),
                new LogoutMenuItem(8, console),
                new AutoSaveMenuItem(9, console)
        );
        while(true){
            if(context.get("currentUser") == null)
                nonAuthenticatedMenu.display();
            else
                mainMenu.display();
        }
    }
    public void initFlights(int count, FlightController flightController) {
        List<Flight> flights = flightController.getFlights();
        if(flights.size() == 0){
            flights = new FlightGenerator().generateFlights(count);
            flightController.getFlights().addAll(flights);
        }
    }
}
