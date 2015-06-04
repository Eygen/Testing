package com.epam.zt.testing.service;

import com.epam.zt.testing.dao.DaoFactory;
import com.epam.zt.testing.dao.RoleDao;
import com.epam.zt.testing.model.Role;

import java.util.List;

import static com.epam.zt.testing.dao.DaoFactory.Type.JDBC;

public class RoleService {

    public static Role findById(int id) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        RoleDao roleDao = factory.getRoleDao();
        Role role = roleDao.findById(id);
        roleDao.close();
        return role;
    }

    public static Role findByName(String roleName) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        RoleDao roleDao = factory.getRoleDao();
        Role role = roleDao.findByName(roleName);
        roleDao.close();
        return role;
    }

    public static List<Role> findAll() {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        RoleDao roleDao = factory.getRoleDao();
        List<Role> roles = roleDao.findAll();
        roleDao.close();
        return roles;
    }
}
