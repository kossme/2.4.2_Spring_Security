/*
package web.Dao;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Repository;
import web.Model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao  {

    @Override
    public List<User> listUsers() {

        TypedQuery<User> query =
                entityManager.createQuery("SELECT e FROM User e", User.class);
        List<User> results = query.getResultList();
        return results;
    }

    @Override
    public void createUsersTable() {
        System.out.println("creating users table");
        entityManager.createNativeQuery("create table if not exist users(ID BIGINT PRIMARY KEY AUTO_INCREMENT, firstname VARCHAR(30), lastName VARCHAR(30), email varchar(30))");
*/
/*        entityManager.persist(new User("User1", "Lastname1", "user1@mail.ru"));
        entityManager.persist(new User("User2", "Lastname2", "user2@mail.ru"));
        entityManager.persist(new User("User3", "Lastname3", "user3@mail.ru"));
        entityManager.persist(new User("User4", "Lastname4", "user4@mail.ru"));*//*

    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
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
        String sqlQuery = "update users set firstName= '" +  user.getFirstName() +
                "', lastName= '" + user.getLastName() +
                "',  email= '" + user.getEmail() +
                "' where id=" +user.getId();
        entityManager.createNativeQuery(sqlQuery).executeUpdate();
    }

    @Override
    public User findUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void clearUsersTable() {

    }

    public User findByUsername(String username) {
        String Query = "SELECT * FROM USER u WHERE u.USERNAME = '" + username + "'";
        return (User) entityManager.createQuery(Query).getSingleResult();
    }
}
*/
