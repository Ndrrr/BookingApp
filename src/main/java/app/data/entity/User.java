package app.data.entity;

import app.Config;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User extends Entity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static int idCounter = 0; // Config.getInstance().getLastFlightID();
    private String username;
    private String password;
    private List<Booking> bookingList;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        bookingList = new ArrayList<>();
    }
    public User(int id, String username, String password) {
        super(id);
        this.username = username;
        this.password = password;
        bookingList = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }
    public boolean removeBooking(Booking booking){
        return bookingList.remove(booking);
    }
    public void addBooking(Booking booking){
        bookingList.add(booking);
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + super.getId() + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    protected int getIdCounter() {
        // Config.getInstance().setLastUserID(idCounter + 1);
        return idCounter++;
    }
}
