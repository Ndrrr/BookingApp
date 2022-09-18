package app.data.dao;

import app.data.Db;
import app.data.entity.Entity;
import app.data.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<User>{
    private static final Db db = Db.getInstance();
    private static final String path = "users.bin";
    private List<User> userList;
    public UserDao(){
        userList = new ArrayList<>();
    }
    public UserDao(List<User> userList){
        this.userList = userList;
    }

    @Override
    public List<User> get() {
        return userList;
    }

    public Optional<User> getUserByUsername(String username){
        return userList.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }
    @Override
    public void save() {
        db.save(userList, path);
    }

    @Override
    public void load() {
        List<Entity> list = db.load(path).orElse(new ArrayList<Entity>());
        boolean match = list.stream()
                .allMatch(e -> e instanceof User);
        if(match){
            userList = new ArrayList<>(
                    list.stream()
                            .map(e -> (User) e)
                            .toList());
        }
    }
}
