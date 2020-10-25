package web.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import web.Model.Role;

import java.util.List;

public interface RoleDao {

    void checkIfRoleExistOraddNewRole(String roleName);

    List<Role> listAllRoles();
}
