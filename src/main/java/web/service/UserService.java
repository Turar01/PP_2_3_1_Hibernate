package web.service;

import org.springframework.stereotype.Component;
import web.model.User;

import java.util.List;

@Component
public interface UserService {
    List<User> getAllUsers();

    User getUser(long id);

    void save(User user);

    void delete(long id);

    void update(long id, User updatedUser);
}