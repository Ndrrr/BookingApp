package app.ui.menu.item;

import app.Context;
import app.ui.menu.command.EmptyCommand;
import app.ui.console.Console;
import app.util.InputUtil;

import java.util.Map;

public class AutoSaveMenuItem extends MenuItem {
    private Context context = Context.getInstance();
    private Console console;

    public AutoSaveMenuItem(int id, Console console) {
        super(id, new EmptyCommand());
        this.console = console;
    }

    @Override
    public String getDescription() {
        if(context.get("autoSave").equals(true))
            return "Disable autosave";
        return "Enable autosave";
    }

    @Override
    protected Map<String, String> getInput() {
        boolean userResp = InputUtil.askYesNo("Do you want to enable autosave?");
        if (userResp) {
            if(context.get("autoSave").equals(true))
                context.put("autoSave", false);
            else
                context.put("autoSave", true);
        }
        return null;
    }

    @Override
    protected MenuItemStatus response(Map <String, String> data) {
        console.println(console.lineSeparator());
        console.println("Autosave state changed successfully");
        return MenuItemStatus.NEXT;
    }
}
