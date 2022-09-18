package app.command;

import app.Context;
import app.controller.BookingController;
import app.controller.UserController;
import app.data.entity.Booking;
import app.data.entity.User;

import java.util.Map;
import java.util.Optional;

public class CancelBookingCommand implements Command{
    private final Context context = Context.getInstance();
    @Override
    public void execute(Map<String, String> data) {
        String bookingIdStr = data.get("bookingId");
        if(bookingIdStr.equals("null")){
            context.put("errorMsg", "Exited without canceling");
            return;
        }
        int bookingId = Integer.parseInt(bookingIdStr);
        BookingController bookingController= (BookingController) context.get("bookingController");
        String username = (String)context.get("currentUser");
        Optional<Booking> bookingOptional = bookingController.getBooking(bookingId);
        Booking booking = null;
        if(bookingOptional.isPresent()) {
            booking = bookingOptional.get();
        }
        else{
            context.put("errorMsg", "Booking id is incorrect");
            return;
        }
        if(booking.getUser().getUsername().equals(username)){
            bookingController.cancelBooking(booking.getUser(), booking);
        }

    }
}
