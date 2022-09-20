package app.ui.menu.item;

import app.Context;
import app.ui.menu.command.LoginCommand;
import app.ui.console.Console;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginMenuItem extends MenuItem {
    private final Console console;
    private final Context context = Context.getInstance();
    public LoginMenuItem(int id, Console console) {
        super(id, new LoginCommand());
        this.console = console;
    }
    @Override
    public String getDescription() {
        return "Login";
    }
    @Override
    protected Map<String, String> getInput() {
        console.print("Enter you username: ");
        String username = console.nextLine();
        console.print("Enter you password: ");
        String password = console.nextLine();
        Map<String, String> input = new HashMap<>();
        input.put("username", username);
        input.put("password", password);
        return input;
    }

    @Override
    protected MenuItemStatus response(Map <String, String> data) {
        String errorMsg = context.getErrorMessage();
        console.println(console.lineSeparator());
        console.println(Objects.requireNonNullElse(errorMsg, "Logged in successfully"));
        console.waitForEnter();
        return MenuItemStatus.NEXT;
    }

}
