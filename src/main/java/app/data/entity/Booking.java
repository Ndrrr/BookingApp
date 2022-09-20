package app.data.entity;

import app.Config;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class Booking extends Entity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private static int idCounter = 0; // Config.getInstance().getLastBookingID();
    private Flight flight;
    private List<Passenger> passengerList;
    private User user;

    public Booking(User user, Flight flight, List<Passenger> passengerList) {
        this.user = user;
        this.flight = flight;
        this.passengerList = passengerList;
    }

    public Booking(int id, User user, Flight flight, List<Passenger> passengerList) {
        super(id);
        this.user = user;
        this.flight = flight;
        this.passengerList = passengerList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Flight getFlight() {
        return flight;
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }
    public String prettyFormat(){
        StringBuilder sb = new StringBuilder();
        sb.append("Booking id: ").append(getId());
        sb.append("\nFlight info:\n");
        sb.append(flight.prettyFormat()).append("\n");
        sb.append("Passenger list:\n");
        passengerList.forEach(passenger -> sb.append("--- ")
                .append(passenger.getFirstName())
                .append(" ")
                .append(passenger.getLastName())
                .append("\n"));
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Booking{" +
                "flight=" + flight.prettyFormat() +
                ", passengerList=" + passengerList +
                ", user=" + user +
                '}';
    }

    @Override
    protected int getIdCounter() {
        //Config.getInstance().setLastBookingID(idCounter + 1);
        return idCounter++;
    }
}
