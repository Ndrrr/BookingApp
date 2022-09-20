import app.data.dao.UserDao;
import app.data.entity.User;
import app.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest {
    private UserService userService;
    @BeforeEach
    void setUp() {
        userService = new UserService(new UserDao());
        User user = new User("user", "changeMe");
        userService.addUser(user);
    }
    @Test
    void testAddUser(){
        int expected = userService.getUsers().size() + 1;
        User user = new User("user", "dontChangeMe");
        userService.addUser(user);
        int result = userService.getUsers().size();
        assertEquals(expected, result);
    }
    @Test
    void testRemoveUser(){
        int id = userService.getUsers().get(0).getId();
        userService.removeUser(id);
        int expected = 0;
        int result = userService.getUsers().size();
        assertEquals(expected, result);
    }
    @Test
    void testRemoveFakeUser(){
        List<User> list = userService.getUsers();
        int[] id = {92838323};
        while(list.stream().anyMatch(user -> user.getId() == id[0])){
            id[0]++;
            System.out.println(id[0]);
        }
        userService.removeUser(id[0]);
        int expected = 1;
        int result = userService.getUsers().size();
        assertEquals(expected, result);
    }
    @Test
    void testUpdateUser(){
        User user = userService.getUsers().get(0);
        user.setUsername("newUsername");
        userService.updateUser(1, user);
        String expected = "newUsername";
        String result = userService.getUsers().get(0).getUsername();
        assertEquals(expected, result);
    }
    @Test
    void testUpdateFakeUser(){
        User user = new User("user", "dontChangeMe");
        userService.updateUser(92838323, user);
        String expected = "user";
        String result = userService.getUsers().get(0).getUsername();
        assertEquals(expected, result);
    }

}
