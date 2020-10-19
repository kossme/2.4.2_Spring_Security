package web.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.Model.User;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Long> {

    User findByUsername(String username);

    List<User> findAll();

    void removeUserById(Long id);

    @Query("update User c set c.firstName = :firstName, c.lastName = :lastname, c.email = :email WHERE c.id = :userId")
    void setUserInfoById(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("email") String email, @Param("userId") long userId);




/*
    void add(User user);

    List<User> listUsers();

    void createUsersTable();

    void removeUser(long id);

    User findUserById(long id);

    void clearUsersTable();

    void updateUser(User user);

    User findByUsername(String username);
*/

    }
