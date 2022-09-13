package app.service;

import app.data.dao.BookingDao;
import app.data.entity.Booking;

import java.util.List;

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
    public Booking getBooking(int id){
        return bookingDao.get(id).orElse(null);
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
}
