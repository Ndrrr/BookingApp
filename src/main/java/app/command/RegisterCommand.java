package app.command;

import app.Context;
import app.controller.UserController;

import java.util.Map;

public class RegisterCommand implements Command{
    Context context = Context.getInstance();


    @Override
    public void execute(Map<String, String> data) {
        UserController userController = (UserController) context.get("userController");
        String username = data.get("username");
        String password = data.get("password");
        String confirmPassword = data.get("confirmPassword");
        if(validatePassword(password, confirmPassword)){
            boolean success = userController.register(username, password);
            if(!success){
                context.put("errorMsg", "Username already exists");
            }
        }
    }

    private boolean validatePassword(String password, String confirmPassword){
        if(!password.equals(confirmPassword)){
            context.put("errorMsg", "Passwords do not match");
            return false;
        }
        if(password.length() < 8 ||
                !password.matches(".*[a-z].*") ||
                !password.matches(".*[A-Z].*") ||
                !password.matches(".*[0-9].*")){
            context.put("errorMsg", "Password must be at least 8 characters long with 1 uppercase letter, 1 lowercase letter and 1 number");
            return false;
        }
        return true;
    }
}
