package web.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import web.Dao.RoleDao;
import web.Dao.UserDao;
import web.Model.Role;
import web.Model.User;


@Service
@Transactional
public class UserServiceImpl implements UserDetailsService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean add(User user) {
        User userfromDB = userDao.findByUsername(user.getUsername());
        if (userfromDB != null) {
            return false;
        }
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
        return true;
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if(user==null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User findUserById(long id) {
        Optional<User> userfromDB = userDao.findById(id);
        return userfromDB.orElse(new User());
    }

    public List<User> listUsers() {
        return userDao.findAll();
    }

    public boolean removeUser(long id) {
        if (userDao.findById(id).isPresent()) {
            userDao.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateUser(String firstName, String lastName, String email, long id){
        if (userDao.findById(id).isPresent()) {
            userDao.setUserInfoById(firstName, lastName, email, id);
            return true;
        }
        return false;
    }

/*
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public List<User> listUsers() {
       return userDao.listUsers();
    }

    @Override
    public void createUsersTable() {
        userDao.createUsersTable();
    }

    @Override
    public void removeUser(long id) {
        userDao.removeUser(id);
    }

    @Override
    public User findUserById(long id) {
        return userDao.findUserById(id);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

*/

}