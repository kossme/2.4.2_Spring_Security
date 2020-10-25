package web.Dao;

import org.springframework.stereotype.Repository;
import web.Model.User;
import web.Model.Role;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

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
            entityManager.persist(new Role(roleName));
        }
    }

    @Override
    public List<Role> listAllRoles() {
        TypedQuery<Role> query =
                entityManager.createQuery("SELECT e FROM Role e", Role.class);
        List<Role> results = query.getResultList();
        return results;
    }


}
