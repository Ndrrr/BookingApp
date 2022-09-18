package app.ui.menu.item;

import app.Context;
import app.command.RegisterCommand;
import app.ui.console.Console;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegisterMenuItem extends MenuItem {
    Console console;
    Context context = Context.getInstance();
    public RegisterMenuItem(int id, Console console) {
        super(id, new RegisterCommand());
        this.console = console;
    }

    @Override
    public String getDescription() {
        return "Register";
    }

    @Override
    protected Map<String, String> getInput() {
        console.print("Enter you username: ");
        String username = console.nextLine();
        console.print("Enter you password: ");
        String password = console.nextLine();
        console.print("Enter your password again: ");
        String password2 = console.nextLine();
        Map<String, String> input = new HashMap<>();
        input.put("username", username);
        input.put("password", password);
        input.put("confirmPassword", password2);
        return input;
    }

    @Override
    protected MenuItemStatus response(Map <String, String> data) {
        String errorMsg = context.getErrorMessage();
        console.println(console.lineSeparator());
        console.println(Objects.requireNonNullElse( errorMsg, "Registered successfully"));
        console.waitForEnter();
        if(errorMsg != null){
            return MenuItemStatus.RESTART;
        }else{
            return MenuItemStatus.NEXT;
        }
    }
}
