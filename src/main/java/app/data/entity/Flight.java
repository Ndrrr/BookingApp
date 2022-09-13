package app.data.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Flight extends Entity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static int idCounter = 0;
    private String flightDesignation;
    private Airport airportFrom;
    private Airport airportTo;
    private Airline airline;
    private LocalDateTime dateTime;
    private Duration duration;
    private int seats;
    private Set<Passenger> passengerList;

    public Flight(String flightDesignation, Airport airportFrom, Airport airportTo,
                  Airline airline, LocalDateTime dateTime, Duration duration,
                  int seats, Set<Passenger> passengerList) {
        this.airportFrom = airportFrom;
        this.airportTo = airportTo;
        this.airline = airline;
        this.flightDesignation = flightDesignation;
        this.dateTime = dateTime;
        this.duration = duration;
        this.seats = seats;
        this.passengerList = passengerList;
    }

    public Flight(int id, String flightDesignation, Airport airportFrom,
                  Airport airportTo, Airline airline, LocalDateTime dateTime,
                  Duration duration, int seats, Set<Passenger> passengerList) {
        super(id);
        this.airportFrom = airportFrom;
        this.airportTo = airportTo;
        this.airline = airline;
        this.flightDesignation = flightDesignation;
        this.dateTime = dateTime;
        this.duration = duration;
        this.seats = seats;
        this.passengerList = passengerList;
    }

    public Airport getAirportFrom() {
        return airportFrom;
    }

    public Airport getAirportTo() {
        return airportTo;
    }

    public Airline getAirline() {
        return airline;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Duration getDuration() {
        return duration;
    }
    public String getFormattedTime() {
        return dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

    public int getSeats() {
        return seats;
    }
    public String getFlightDesignation() {
        return flightDesignation;
    }
    public Set<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setFlightDesignation(String flightDesignation) {
        this.flightDesignation = flightDesignation;
    }

    public void setAirportFrom(Airport airportFrom) {
        this.airportFrom = airportFrom;
    }

    public void setAirportTo(Airport airportTo) {
        this.airportTo = airportTo;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setPassengerList(Set<Passenger> passengerList) {
        this.passengerList = passengerList;
    }

    public void addPassenger(Passenger passenger){
        if(passengerList == null) passengerList = new HashSet<>();
        passengerList.add(passenger);
    }
    public void removePassenger(Passenger passenger){
        if(passengerList == null) return;
        passengerList.remove(passenger);
    }
    public String prettyFormat(){
        return String.format("| %3.3s | %5.5s | %16.16s | %30.30s ----> %30.30s | %20.20s | %3.3s |",
                getId(), flightDesignation, getFormattedTime(), airportFrom.getLocation(),
                airportTo.getLocation(), airline.getName(),  seats);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightDesignation='" + flightDesignation + '\'' +
                ", airportFrom=" + airportFrom +
                ", airportTo=" + airportTo +
                ", airline=" + airline +
                ", dateTime=" + dateTime +
                ", duration=" + duration +
                ", seats=" + seats +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Flight flight = (Flight) o;
        return getId() == flight.getId() && flightDesignation.equals(flight.flightDesignation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), flightDesignation, getId());
    }

    @Override
    protected int getIdCounter(){
        return idCounter++;
    }
}
