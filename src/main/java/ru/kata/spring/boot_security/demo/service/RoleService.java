package ru.kata.spring.boot_security.demo.service;

import java.util.Set;
import ru.kata.spring.boot_security.demo.model.Role;

public interface RoleService {
    Set<Role> getRoles();

    Role findById(Long id);

    Role findByName(String name);
}
