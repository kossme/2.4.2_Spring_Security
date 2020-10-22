package web.Dao;

import org.springframework.stereotype.Repository;
import web.Model.User;
import web.Model.Role;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void checkIfRoleExistOraddNewRole(String roleName) {
        String query;
        query= "SELECT u FROM Role u WHERE u.name = '" + roleName + "'";
        try {
            Role role = (Role) entityManager.createQuery(query).getSingleResult();
        } catch (NoResultException e) {
/*            query = "INSERT INTO ROLES (NAME) VALUES ('" + roleName + "') ";
            entityManager.createNativeQuery(query).executeUpdate();*/
            entityManager.persist(new Role(roleName));
        }
    }
}
