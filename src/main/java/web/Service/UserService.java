package web.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
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
    RoleService roleService;

    @Autowired
    UserDao userDao;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = user.getRoles();
        if(user.getRoles()==null) {
            user.setRoles(Collections.singleton(roleService.getRoleByName("ROLE_USER")));
        }
        for(Role e : user.getRoles()) {
            if(e.getName().equals("ROLE_USER")) {
                e.setId(1L);
            }
            if(e.getName().equals("ROLE_ADMIN")) {
                e.setId(2L);
            }
        }
        userDao.save(user);
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

    public void removeUser(long id) {
        userDao.removeUser(id);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }


}