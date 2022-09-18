package app.service;

import app.data.dao.BookingDao;
import app.data.entity.Booking;
import app.data.entity.Flight;
import app.data.entity.Passenger;
import app.data.entity.User;

import java.util.List;
import java.util.Optional;

public class BookingService {
    private final BookingDao bookingDao;

    public BookingService(BookingDao bookingDao){
        this.bookingDao = bookingDao;
    }

    public void addBooking(Booking booking){
        bookingDao.add(booking);
    }
    public void removeBooking(Booking booking){
        bookingDao.delete(booking);
    }
    public void removeBooking(int id){
        bookingDao.delete(id);
    }
    public void updateBooking(int id, Booking booking){
        bookingDao.update(id, booking);
    }
    public Optional<Booking> getBooking(int id){
        return bookingDao.get(id);
    }
    public List<Booking> getBookings(){
        return bookingDao.get();
    }
    public void saveToDb(){
        bookingDao.save();
    }
    public void loadFromDb(){
        bookingDao.load();
    }

    public Booking makeBooking(User user, Flight flight, List<Passenger> passengerList){
        Booking booking = new Booking(user, flight, passengerList);
        addBooking(booking);
        return booking;
    }
    public boolean cancelBooking(User user, Booking booking){
        user.removeBooking(booking);
        booking.setUser(null);
        return true;
    }
}
