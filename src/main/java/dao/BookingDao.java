package dao;

import entity.Booking;
import entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class BookingDao implements Dao<Booking> {
    private final String path = "bookings.txt";
    List<Booking> bookingList;
    public BookingDao(){
        bookingList = new ArrayList<>();
    }
    public BookingDao(List<Booking> bookingList){
        this.bookingList = bookingList;
    }
    @Override
    public List<Booking> get() {
        return bookingList;
    }
    @Override
    public void save() {
        Db.save(bookingList, path);
    }

    @Override
    public void load() {
        List<Entity> list = Db.load(path).orElse(new ArrayList<Entity>());
        boolean match = list.stream()
                .allMatch(e -> e instanceof Booking);
        if(match){
            bookingList = new ArrayList<>(
                    list.stream()
                            .map(e -> (Booking) e)
                            .toList());
        }
    }
}
