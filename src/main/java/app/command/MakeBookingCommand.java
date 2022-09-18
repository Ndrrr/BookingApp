package app.command;

import app.Context;
import app.controller.BookingController;
import app.controller.FlightController;
import app.controller.UserController;
import app.data.entity.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class MakeBookingCommand implements Command {
    private final Context context = Context.getInstance();
    private final FlightController flightController = (FlightController) context.get("flightController");
    private final BookingController bookingController = (BookingController) context.get("bookingController");
    private final UserController userController = (UserController) context.get("userController");
    @Override
    public void execute(Map<String, String> data) {
        String username = (String) context.get("currentUser");
        Optional<User> user = userController.getUserByUsername(username);

        makeBooking(data, user);

    }

    private void makeBooking(Map<String, String> data, Optional<User> user){
        int passengerCount = Integer.parseInt(data.get("passengerCount"));
        String flightDesignation = data.get("flightDesignation");
        Optional<Flight> flightOptional = flightController.getFlightByDesignation(flightDesignation);
        if(flightOptional.isEmpty()){
            context.put("errorMsg", "Flight not found with given parameters");
            return;
        }
        Flight flight = flightOptional.get();
        List<Passenger> passengerList = new ArrayList<>();
        for(int i = 0; i < passengerCount; i++){
            String passengerName = data.get("pName" + i);
            String passengerGender = data.get("pSurname" + i);
            passengerList.add(new Passenger(passengerName, passengerGender));
        }
        if(user.isPresent()) {
            User bookingOwner = user.get();
            Booking booking = bookingController.makeBooking(bookingOwner,flight, passengerList);
            flightController.addPassengerToFlight(flight, passengerList);
            userController.addBookingToUser(bookingOwner, booking);
        }
    }
}
