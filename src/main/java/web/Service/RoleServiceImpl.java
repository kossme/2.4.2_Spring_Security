package web.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import web.Dao.RoleDao;
import web.Model.Role;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@ComponentScan(basePackages = "web")
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    public void checkIfRoleExistOraddNewRole(String roleName) {
        roleDao.checkIfRoleExistOraddNewRole(roleName);
    }

    @Override
    public List<Role> listAllRoles() {
        return roleDao.listAllRoles();
    }

}
