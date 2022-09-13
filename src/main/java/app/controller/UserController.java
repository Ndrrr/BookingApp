package app.controller;

import app.data.entity.User;
import app.service.UserService;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    public void addUser(User user){
        userService.addUser(user);
    }
    public void removeUser(User user){
        userService.removeUser(user);
    }
    public void removeUser(int id){
        userService.removeUser(id);
    }
    public void updateUser(int id, User user){
        userService.updateUser(id, user);
    }
    public User getUser(int id){
        return userService.getUser(id);
    }
    public void saveToDb(){
        userService.saveToDb();
    }
    public void loadFromDb(){
        userService.loadFromDb();
    }
    public boolean login(String username, String password){
        return userService.login(username, password);
    }
    public boolean register(String username, String password){
        return userService.register(username, password);
    }
}
