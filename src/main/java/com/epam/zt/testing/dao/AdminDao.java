package com.epam.zt.testing.dao;

import com.epam.zt.testing.model.Admin;

public interface AdminDao extends Dao<Admin> {
    Admin findAdmin(String login, String password);
}
