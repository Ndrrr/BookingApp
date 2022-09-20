package app.ui.menu.command;

import java.util.Map;

public interface Command {
    void execute(Map<String, String> data);
}
