package app.util;

import app.controller.FlightController;
import app.data.entity.Airport;
import app.data.entity.Flight;
import app.ui.console.Console;
import app.ui.console.ProdConsole;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;

public class InputUtil {
    static Console console = new ProdConsole();
    public static boolean checkInt(String tmp){
        if(tmp == null) return false;
        try{
            Integer.parseInt(tmp);
        }catch (NumberFormatException nfe){
            return false;
        }
        return true;
    }
    public static int getCorrectInt(){
        String tmp = console.next();
        if(checkInt(tmp)){
            console.nextLine();
            return Integer.parseInt(tmp);
        }
        console.print("Please enter a valid number: ");
        return getCorrectInt();
    }
    public static Optional<Integer> getCorrectInt(Predicate<Integer> predicate, String errorMessage){
        String tmp = console.next();
        if(tmp.equals("exit")){
            return Optional.empty();
        }
        if(checkInt(tmp)){
            int parsed = Integer.parseInt(tmp);
            if(predicate.test(parsed)) {
                console.nextLine();
                return Optional.of(Integer.parseInt(tmp));
            }
            else{
                console.println(errorMessage);
            }
        }
        console.print("Please enter a valid number [`exit` for leaving]: ");
        return getCorrectInt(predicate, errorMessage);
    }

    public static boolean askYesNo(String question){
        console.print(question + "[y/*]: ");
        String answer = console.nextLine().toLowerCase();
        return answer.equals("y");
    }

    public static String getCorrectString(Predicate<String> predicate, String errorMessage){
        String tmp = console.nextLine();
        if(predicate.test(tmp)) {
            return tmp;
        }
        else{
            console.print(errorMessage);
        }
        return getCorrectString(predicate, errorMessage);
    }

    public static String getCorrectDate( String errorMessage){
        String tmp = console.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try{
            sdf.parse(tmp);
            return tmp;
        }
        catch(ParseException e){
            console.print(errorMessage);
        }
        return getCorrectDate(errorMessage);
    }

    public static Map<String, String> getFlightData(){
        Map<String, String> data = new HashMap<>();
        console.print("Enter origin: ");
        String origin = validateCity("origin");
        data.put("origin", origin);

        console.print("Enter destination: ");
        String destination = validateCity("destination");
        data.put("destination", destination);

        console.print("Enter date (dd.MM.yyyy): ");
        String date = validateDate();
        data.put("date", date);

        console.print("Enter passenger count: ");
        int passengerCount = InputUtil.getCorrectInt();
        data.put("passengerCount", String.valueOf(passengerCount));
        return data;
    }
    public static List<Flight> deserializeFlights(String flights, FlightController flightController){

        List<Flight> flightList = new ArrayList<>();
        String[] flightStrings = flights.split(";");
        for(String flightString : flightStrings){
            String[] flightData = flightString.split(",");
            Flight flight = flightController.getFlight(Integer.parseInt(flightData[0])).get();
            flightList.add(flight);
        }
        return flightList;
    }
    public static String validateCity(String cityInfo){
        return InputUtil.getCorrectString( city ->
                        Arrays.stream(Airport.values()).anyMatch(airport -> airport.getCity().equalsIgnoreCase(city)),
                "Invalid " + cityInfo + ", please enter again: ");
    }
    public static String validateDate(){
        return InputUtil.getCorrectDate("Invalid date format, please enter again [dd.MM.yyyy]: ");
    }
    public static String validateDesignation(FlightController flightController){
        return InputUtil.getCorrectString( designation ->
                        flightController.getFlights().stream()
                                .anyMatch(flight -> flight.getFlightDesignation().equalsIgnoreCase(designation)),
                "Invalid designation, please enter again: ");
    }

}
