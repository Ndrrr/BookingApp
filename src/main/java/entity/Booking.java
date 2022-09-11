package entity;

import java.io.Serializable;
import java.util.List;

public class Booking extends Entity implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int idCounter = 0;
    private Flight flight;
    private List<Passenger> passengerList;

    public Booking(Flight flight, List<Passenger> passengerList) {
        this.flight = flight;
        this.passengerList = passengerList;
    }

    public Booking(int id, Flight flight, List<Passenger> passengerList) {
        super(id);
        this.flight = flight;
        this.passengerList = passengerList;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "flight=" + flight +
                ", passengerList=" + passengerList +
                '}';
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

    @Override
    protected int getIdCounter() {
        return idCounter++;
    }
}
