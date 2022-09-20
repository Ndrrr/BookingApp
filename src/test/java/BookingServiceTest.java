import app.data.dao.BookingDao;
import app.data.entity.Booking;
import app.data.entity.Passenger;
import app.data.entity.User;
import app.service.BookingService;
import app.util.FlightGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingServiceTest {
    private BookingService bookingService;
    private final FlightGenerator flightGenerator = new FlightGenerator();
    @BeforeEach
    void setUp() {
        bookingService = new BookingService(new BookingDao());
        User user = new User("user", "changeMe");
        bookingService.addBooking(
                new Booking(user,
                        flightGenerator.generateFlight(),
                        List.of(new Passenger("John", "Doe"))));
    }
    @Test
    void testAddBooking(){
        int expected = bookingService.getBookings().size() + 1;
        User user = new User("user", "dontChangeMe");
        bookingService.addBooking(
                new Booking(user,
                        flightGenerator.generateFlight(),
                        List.of(new Passenger("John", "Doe"))));
        int result = bookingService.getBookings().size();
        assertEquals(expected, result);
    }
    @Test
    void testRemoveBooking(){
        int id = bookingService.getBookings().get(0).getId();
        bookingService.removeBooking(id);
        int expected = 0;
        int result = bookingService.getBookings().size();
        assertEquals(expected, result);
    }
    @Test
    void testRemoveFakeBooking(){
        List<Booking> list = bookingService.getBookings();
        int[] id = {92838323};
        while(list.stream().anyMatch(booking -> booking.getId() == id[0])){
            id[0]++;
            System.out.println(id[0]);
        }
        bookingService.removeBooking(id[0]);
        int expected = 1;
        int result = bookingService.getBookings().size();
        assertEquals(expected, result);
    }
    @Test
    void testUpdateBooking(){
        Booking booking = bookingService.getBookings().get(0);
        booking.setPassengerList(List.of(new Passenger("John", "Doe"), new Passenger("Jane", "Doe")));
        bookingService.updateBooking(0, booking);
        int expected = 2;
        int result = bookingService.getBookings().get(0).getPassengerList().size();
        assertEquals(expected, result);
    }

    @Test
    void testMakeBooking(){
        User user = new User("user", "dontChangeMe");
        bookingService.makeBooking(user, flightGenerator.generateFlight(), List.of(new Passenger("John", "Doe")));
        int expected = 2;
        int result = bookingService.getBookings().size();
        assertEquals(expected, result);
    }
    @Test
    void testCancelBooking(){
        User user = new User("user", "dontChangeMe");
        bookingService.makeBooking(user, flightGenerator.generateFlight(), List.of(new Passenger("John", "Doe")));
        int id = user.getBookingList().get(0).getId();
        bookingService.cancelBooking(user, bookingService.getBookingOfUserById(user, id).get());
        int expected = 0;
        int result = user.getBookingList().size();
        assertEquals(expected, result);
    }
}
