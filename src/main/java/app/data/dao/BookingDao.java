package app.data.dao;

import app.data.Db;
import app.data.entity.Booking;
import app.data.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class BookingDao implements Dao<Booking> {
    private static final Db db = Db.getInstance();
    private final String path = "bookings.bin";
    private List<Booking> bookingList;
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
        db.save(bookingList, path);
    }

    @Override
    public void load() {
        List<Entity> list = db.load(path).orElse(new ArrayList<Entity>());
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
