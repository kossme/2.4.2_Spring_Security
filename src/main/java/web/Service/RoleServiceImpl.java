package web.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import web.Dao.RoleDao;

import javax.transaction.Transactional;

@Service
@Transactional
@ComponentScan(basePackages = "web")
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    public void checkIfRoleExistOraddNewRole(String roleName){
        roleDao.checkIfRoleExistOraddNewRole(roleName);
    }

}
