package com.epam.zt.testing.dao;

import com.epam.zt.testing.model.Role;

public interface RoleDao extends Dao<Role> {
    Role findByName(String roleName);
}
