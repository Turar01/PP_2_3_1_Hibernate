package web.dao;


import org.springframework.stereotype.Repository;
import web.model.User;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    List<User> users = new ArrayList<>();
    public List<User> getAllUsers() {
        String HQL = "from User";
        users = entityManager.createQuery(HQL, User.class).getResultList();
        return users;
    }

    public User getUser(long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    public void save(User user) {
        entityManager.persist(user);
        entityManager.flush();

    }

    public void delete(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
        entityManager.flush();
    }

    public void update(long id, User updatedUser) {
        User user = entityManager.find(User.class, id);
        user.setName(updatedUser.getName());
        user.setAge(updatedUser.getAge());
        entityManager.merge(user);
        entityManager.flush();
    }




}




