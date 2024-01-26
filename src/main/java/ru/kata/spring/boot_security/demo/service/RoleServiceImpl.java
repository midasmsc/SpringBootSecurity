package ru.kata.spring.boot_security.demo.service;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.Dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;


@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Set<Role> getRoles() {
        return roleDao.getRoles();
    }

    public Role findById(Long id) {
        return roleDao.findById(id);
    }

    public Role findByName(String name) {
        return roleDao.findByName(name);
    }
}
