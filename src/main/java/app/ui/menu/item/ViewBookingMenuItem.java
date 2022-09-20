package app.ui.menu.item;

import app.Context;
import app.ui.menu.command.EmptyCommand;
import app.controller.UserController;
import app.data.entity.User;
import app.ui.console.Console;

import java.util.Map;

public class ViewBookingMenuItem extends MenuItem{
    private final Console console;
    private final Context context = Context.getInstance();
    private final UserController userController;
    public ViewBookingMenuItem(int id, Console console, UserController userController) {
        super(id, new EmptyCommand());
        this.console = console;
        this.userController = userController;
    }

    @Override
    public String getDescription() {
        return "View Bookings";
    }

    @Override
    protected Map<String, String> getInput() {
        return null;
    }

    @Override
    protected MenuItemStatus response(Map <String, String> data) {
        User userOptional = userController.getUserByUsername((String)context.get("currentUser")).get();
        console.println(console.lineSeparator());
        userOptional.getBookingList().forEach(booking -> console.println(booking.prettyFormat()));
        console.waitForEnter();
        return MenuItemStatus.NEXT;
    }
}
