package app.controller;

import app.data.entity.Booking;
import app.data.entity.Flight;
import app.data.entity.Passenger;
import app.data.entity.User;
import app.service.BookingService;

import java.util.List;
import java.util.Optional;

public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }


    public void addBooking(Booking booking){
        bookingService.addBooking(booking);
    }
    public void removeBooking(Booking booking){
        bookingService.removeBooking(booking);
    }
    public void removeBooking(int id){
        bookingService.removeBooking(id);
    }
    public void updateBooking(int id, Booking booking){
        bookingService.updateBooking(id, booking);
    }
    public Optional<Booking> getBooking(int id){
        return bookingService.getBooking(id);
    }
    public List<Booking> getBookings(){
        return bookingService.getBookings();
    }
    public void saveToDb(){
        bookingService.saveToDb();
    }
    public void loadFromDb(){
        bookingService.loadFromDb();
    }

    public Booking makeBooking(User user, Flight flight, List<Passenger> passengerList){
        return bookingService.makeBooking(user, flight, passengerList);
    }
    public boolean cancelBooking(User user, Booking booking){
        return bookingService.cancelBooking(user, booking);
    }
}
