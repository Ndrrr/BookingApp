package app.ui.menu.command;

import app.Context;

import java.util.Map;

public class LogoutCommand implements Command{
    @Override
    public void execute(Map<String, String> data) {
        Context context = Context.getInstance();
        if(data.get("userResp").equals("true")){
            context.put("currentUser", null);
        }
    }
}
