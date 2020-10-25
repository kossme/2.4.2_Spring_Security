package web.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.Model.User;

import java.util.List;

public interface UserDao {

    void save(User user);

    List<User> listUsers();

    void removeUser(long id);

    User findUserById(long id);

    void updateUser(User user);

    User findByUsername(String username);

}
