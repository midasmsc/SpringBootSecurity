package ru.kata.spring.boot_security.demo.Dao;

import java.util.Set;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

@Repository
public interface RoleDao {
    Set<Role> getRoles();

    Role findById(Long id);
    Role findByName(String name);
}
