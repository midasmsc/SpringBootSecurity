package ru.kata.spring.boot_security.demo.Dao;

import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Set<Role> getRoles() {
        return entityManager.createQuery("from Role", Role.class)
                .getResultStream()
                .collect(Collectors.toSet());
    }

    @Override
    public Role findById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role findByName(String name) {
        Query query = entityManager.createQuery("SELECT role FROM Role role WHERE role.name = :name");
        query.setParameter("name", name);
        return (Role) query.getSingleResult();
    }
}
