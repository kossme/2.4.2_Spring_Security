package web.Service;

import web.Model.Role;

import java.util.List;

public interface RoleService {

    void checkIfRoleExistOraddNewRole(String roleName);
    public List<Role> listAllRoles();
    Role getRoleById(Long id);

    Role getRoleByName(String name);
}
