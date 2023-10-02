package web.dao;

import web.model.User;
import java.util.List;

public interface UserDao {
    User getUserById(Long id);
    List<User> getAllUsers();
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
}
