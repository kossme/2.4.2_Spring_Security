
package web.Dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Repository;
import web.Model.User;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao  {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> listUsers() {

        TypedQuery<User> query =
                entityManager.createQuery("SELECT e FROM User e", User.class);
        List<User> results = query.getResultList();
        return results;
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void removeUser(long id) {
        User user  = entityManager.find(User.class, id);
        if (user!=null) {
            entityManager.remove(user);
        }
    }

    @Override
    public void updateUser(User user){
        Session session = entityManager.unwrap(Session.class);
        session.update(user);
    }

    @Override
    public User findUserById(long id) {
        return entityManager.find(User.class, id);
    }

    public User findByUsername(String username) {
        String Query = "SELECT u FROM User u WHERE u.username = '" + username + "'";
        User user;
        try {
            user = (User) entityManager.createQuery(Query).getSingleResult();
            System.out.println(user);
        } catch (NoResultException e) {
            user = null;
        }
        return user;
    }
}

