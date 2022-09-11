package dao;

import entity.Entity;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao<User>{
    private static final String path = "users.txt";
    List<User> userList;
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
    @Override
    public void save() {
        Db.save(userList, path);
    }

    @Override
    public void load() {
        List<Entity> list = Db.load(path).orElse(new ArrayList<Entity>());
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
