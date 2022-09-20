package app.ui.menu.command;

import app.controller.UserController;

import app.Context;

import java.util.Map;

public class LoginCommand implements Command{
    Context context = Context.getInstance();
    @Override
    public void execute(Map<String, String> data) {
        UserController userController = (UserController) context.get("userController");
        boolean success = userController.login(data.get("username"), data.get("password"));
        if(success){
            context.put("currentUser", data.get("username"));
        }else {
            context.put("errorMsg", "Invalid username or password");
        }
    }
}
