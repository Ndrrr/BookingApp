package app.ui.menu.item;

import app.command.Command;

import java.util.Map;

public abstract class MenuItem {
    private final int operationId;
    private final Command command;

    public MenuItem(int id, Command command){
        this.operationId = id;
        this.command = command;
    }
    public MenuItemStatus run(){
        Map<String, String> data = getInput();
        command.execute(data);
        return response(data);
    }

    public int getOperationId() {
        return operationId;
    }
    public abstract String getDescription();
    protected abstract Map<String, String> getInput();
    protected abstract MenuItemStatus response(Map<String, String> data);
}
