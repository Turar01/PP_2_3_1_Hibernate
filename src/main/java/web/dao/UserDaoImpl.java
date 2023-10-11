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

    public List<User> getAllUsers() {
        String HQL = "from User";
        return entityManager.createQuery(HQL, User.class).getResultList();
    }

    public User getUser(long id) {
        return entityManager.find(User.class, id);
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




