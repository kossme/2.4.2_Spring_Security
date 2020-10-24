package web.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import web.Dao.UserDao;
import web.Model.Role;
import web.Model.User;


@Service
@Transactional
@ComponentScan(basePackages = "web")
public class UserService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean add(User user) {
        User userfromDB = userDao.findByUsername(user.getUsername());
        if (userfromDB != null) {
            return false;
        }
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.add(user);
        return true;
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        return user;
    }

    public User findUserById(long id) {
        return userDao.findUserById(id);
    }

    public List<User> listUsers() {
        return userDao.listUsers();
    }

    public void createUsersTable() {
        userDao.createUsersTable();
    }

    public void removeUser(long id) {
        userDao.removeUser(id);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }


}