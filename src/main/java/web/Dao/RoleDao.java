package web.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import web.Model.Role;

public interface RoleDao extends JpaRepository<Role, Long> {
}
