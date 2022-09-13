package app.command;

import java.util.Map;

public interface Command {
    boolean execute(Map<String, String> data);
}
