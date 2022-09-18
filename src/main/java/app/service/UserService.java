package app.service;

import app.data.dao.UserDao;
import app.data.entity.Booking;
import app.data.entity.User;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    public void addUser(User user){
        userDao.add(user);
    }
    public void removeUser(User user){
        userDao.delete(user);
    }
    public void removeUser(int id){
        userDao.delete(id);
    }
    public void updateUser(int id, User user){
        userDao.update(id, user);
    }
    public Optional<User> getUser(int id){
        return userDao.get(id);
    }
    public Optional<User> getUserByUsername(String username){
        return userDao.getUserByUsername(username);
    }
    public List<User> getUsers(){
        return userDao.get();
    }
    public void saveToDb(){
        userDao.save();
    }
    public void loadFromDb(){
        userDao.load();
    }
    public boolean login(String username, String password){
        return userDao.get().stream()
                .anyMatch(u -> u.getUsername().equals(username) && u.getPassword().equals(password));
    }
    public boolean register(String username, String password){
        if(userDao.get().stream()
                .anyMatch(u -> u.getUsername().equals(username))){
            return false;
        }
        userDao.add(new User(username, password));
        return true;
    }
    public void addBookingToUser(User user, Booking booking){
        user.getBookingList().add(booking);
        updateUser(user.getId(), user);
    }
}
