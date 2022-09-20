package app.ui.menu.item;

import app.Context;
import app.command.CancelBookingCommand;
import app.command.Command;
import app.controller.UserController;
import app.data.entity.Booking;
import app.data.entity.User;
import app.ui.console.Console;
import app.util.InputUtil;

import java.util.*;

public class CancelBookingMenuItem extends MenuItem{
    private final Context context = Context.getInstance();
    private final Console console;
    private final UserController userController;
    public CancelBookingMenuItem(int id, Console console, UserController userController) {
        super(id, new CancelBookingCommand());
        this.console = console;
        this.userController = userController;
    }

    @Override
    public String getDescription() {
        return "Cancel Booking";
    }

    @Override
    protected Map<String, String> getInput() {
        User user = userController.getUserByUsername((String)context.get("currentUser")).get();
        console.println(console.lineSeparator());
        List<Booking> bookings = user.getBookingList();
        bookings.forEach(booking -> console.println(booking.prettyFormat()));

        console.print("Enter booking id you want to cancel: ");
        Optional<Integer> id = InputUtil.getCorrectInt(bookingId -> bookings.
                stream().anyMatch(booking -> bookingId == booking.getId()),
                "You dont have booking with given id");
        Map<String, String> input = new HashMap<>();
        input.put("bookingId", String.valueOf(id.orElse(null)));
        return input;
    }

    @Override
    protected MenuItemStatus response(Map<String, String> data) {
        String errorMsg = context.getErrorMessage();
        console.println(console.lineSeparator());
        console.println(Objects.requireNonNullElse(errorMsg, "Cancelled successfully"));
        console.waitForEnter();
        return MenuItemStatus.NEXT;
    }
}
