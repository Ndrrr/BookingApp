package app.ui.menu.item;

import app.Context;
import app.command.EmptyCommand;
import app.ui.console.Console;
import app.util.InputUtil;

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
        boolean userResp = InputUtil.askYesNo("Do you want to save before exit?");
        if (userResp) {
            Context.getInstance().saveApp();
            console.println("Data saved successfully");
        }else {
            console.println("Leaving without saving");
        }
        return null;
    }

    @Override
    protected MenuItemStatus response(Map <String, String> data) {
        System.exit(0);
        return MenuItemStatus.NEXT;
    }
}
