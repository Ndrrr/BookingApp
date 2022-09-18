package app;

import app.ui.console.ProdConsole;
import app.controller.BookingController;
import app.controller.FlightController;
import app.controller.UserController;
import app.data.dao.BookingDao;
import app.data.dao.FlightDao;
import app.data.dao.UserDao;
import app.service.BookingService;
import app.service.FlightService;
import app.service.UserService;

import java.util.HashMap;
import java.util.Map;

public class Context {
    private static Context instance;
    private final Map<String, Object> data;
    private Context() {
        this.data = new HashMap<>();
        init();
    }
    public static Context getInstance(){
        if(instance == null){
            instance = new Context();
        }
        return instance;
    }

    public void put(String key, Object value){
        data.put(key, value);
    }
    public Object get(String key){
        return data.get(key);
    }
    public String getErrorMessage(){
        String message = (String) data.get("errorMsg");
        data.remove("errorMsg");
        return message;
    }
    public void saveApp(){
        FlightController flightController = (FlightController) data.get("flightController");
        flightController.saveToDb();
        BookingController bookingController = (BookingController) data.get("bookingController");
        bookingController.saveToDb();
        UserController userController = (UserController) data.get("userController");
        userController.saveToDb();
        Config.save();

    }
    public void loadApp(){
        FlightController flightController = (FlightController) data.get("flightController");
        flightController.loadFromDb();
        BookingController bookingController = (BookingController) data.get("bookingController");
        bookingController.loadFromDb();
        UserController userController = (UserController) data.get("userController");
        userController.loadFromDb();
        Config.load();
    }
    private void init(){
        data.put("currentUser", null);
        data.put("userService", new UserService(new UserDao()));
        data.put("flightService", new FlightService(new FlightDao()));
        data.put("bookingService", new BookingService(new BookingDao()));
        data.put("userController", new UserController((UserService) data.get("userService")));
        data.put("flightController", new FlightController((FlightService) data.get("flightService")));
        data.put("bookingController", new BookingController((BookingService) data.get("bookingService")));
        data.put("console", new ProdConsole());
        data.put("autoSave", true);
        loadApp();
    }


}
