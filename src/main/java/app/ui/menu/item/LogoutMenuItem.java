package app.ui.menu.item;

import app.Context;
import app.ui.menu.command.LogoutCommand;
import app.ui.console.Console;
import app.util.InputUtil;

import java.util.Map;

public class LogoutMenuItem extends MenuItem {
    private Context context = Context.getInstance();
    private Console console;
    public LogoutMenuItem(int id, Console console) {
        super(id, new LogoutCommand());
        this.console = console;
    }

    @Override
    public String getDescription() {
        return "Log out";
    }

    @Override
    protected Map<String, String> getInput() {
        boolean userResp = InputUtil.askYesNo("Do you want to log out?");
        Map <String, String> input = Map.of("userResp", String.valueOf(userResp));
        return input;
    }

    @Override
    protected MenuItemStatus response(Map <String, String> data) {
        console.println(console.lineSeparator());
        if(context.get("currentUser") == null)
            console.println("Logged out successfully");
        else{
            console.println("Log out failed");
        }
        return MenuItemStatus.NEXT;
    }
}
