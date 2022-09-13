package app.command;

import java.util.Map;

public class EmptyCommand implements Command {
    @Override
    public boolean execute(Map<String, String> data) {
        return false;
    }
}
