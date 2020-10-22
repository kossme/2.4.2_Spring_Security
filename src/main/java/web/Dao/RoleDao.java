package web.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import web.Model.Role;

public interface RoleDao {

    void checkIfRoleExistOraddNewRole(String roleName);
}
