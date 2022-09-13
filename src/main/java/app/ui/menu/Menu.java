package app.ui.menu;

import app.ui.console.Console;
import app.ui.menu.item.MenuItem;
import app.ui.menu.item.MenuItemStatus;
import app.util.InputUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu {
    private final Console console;
    private List<MenuItem> menuItems;
    private final String line = "=============================================================\n";


    public Menu(Console console, MenuItem... menuItems) {
        this.console = console;
        this.menuItems = new ArrayList<>(Arrays.asList(menuItems));
    }


    public Menu display() {
        String commandString = commandStringBuilder();
        console.print(commandString);
        int command = InputUtil.getCorrectInt();
        MenuItemStatus[] status = new MenuItemStatus[1];
        status[0] = MenuItemStatus.RESTART;
        while(status[0] == MenuItemStatus.RESTART) {
            menuItems.stream()
                    .filter(item -> item.getOperationId() == command)
                    .forEach(item -> status[0] = item.run());
        }
        return this;
    }
    private String commandStringBuilder(){
        StringBuilder commandString = new StringBuilder();
        commandString.append(line);
        commandString.append("Flight Booking Manager\n");
        commandString.append(line);
        menuItems.forEach(menuItem -> commandString.append(menuItem.getOperationId() + " - " + menuItem.getDescription() + "\n"));
        commandString.append(line);
        commandString.append("Enter command: ");
        return commandString.toString();
    }
}
