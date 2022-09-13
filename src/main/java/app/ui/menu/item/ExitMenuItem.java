package app.ui.menu.item;

import app.Context;
import app.command.Command;
import app.command.EmptyCommand;
import app.ui.console.Console;

import java.util.Map;

public class ExitMenuItem extends MenuItem {
    private Console console;

    public ExitMenuItem(int id, Console console) {
        super(id, new EmptyCommand());
        this.console = console;
    }

    @Override
    public String getDescription() {
        return "Exit";
    }

    @Override
    protected Map<String, String> getInput() {
        console.println("Do you want to save data before leaving?[y/*]");
        String answer = console.nextLine().toLowerCase();
        if (answer.equals("y")) {
            Context.getInstance().saveApp();
            console.println("Data saved successfully");
        }else {
            console.println("Leaving without saving");
        }
        return null;
    }

    @Override
    protected MenuItemStatus response() {
        System.exit(0);
        return MenuItemStatus.CONTINUE;
    }
}
