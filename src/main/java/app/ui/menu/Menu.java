package app.ui.menu;

import app.Context;
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
    private Context context = Context.getInstance();

    public Menu(Console console, MenuItem... menuItems) {
        this.console = console;
        this.menuItems = new ArrayList<>(Arrays.asList(menuItems));
    }


    public Menu display() {
        String username = (String) context.get("currentUser");
        String commandString = commandStringBuilder();
        if(username != null){
            console.println("Logged in as: " + username);
        }
        console.print(commandString);
        int command = InputUtil.getCorrectInt();
        MenuItemStatus[] status = new MenuItemStatus[1];
        status[0] = MenuItemStatus.RESTART;
        while(status[0] == MenuItemStatus.RESTART) {
            menuItems.stream()
                    .filter(item -> item.getOperationId() == command)
                    .forEach(item -> status[0] = item.run());
        }
        if((boolean)context.get("autoSave")){
            context.saveApp();
        }
        return this;
    }
    private String commandStringBuilder(){
        StringBuilder commandString = new StringBuilder();
        commandString.append(console.bigLineSeparator());
        commandString.append("Flight Booking Manager\n");
        commandString.append(console.bigLineSeparator());
        menuItems.forEach(menuItem -> commandString.append(menuItem.getOperationId() + " - " + menuItem.getDescription() + "\n"));
        commandString.append(console.bigLineSeparator());
        commandString.append("Enter command: ");
        return commandString.toString();
    }
}
