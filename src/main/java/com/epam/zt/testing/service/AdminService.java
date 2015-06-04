package com.epam.zt.testing.service;

import com.epam.zt.testing.dao.AdminDao;
import com.epam.zt.testing.dao.DaoFactory;
import com.epam.zt.testing.model.Admin;

import static com.epam.zt.testing.dao.DaoFactory.Type.JDBC;

public class AdminService {
    public boolean createAdmin(Admin admin) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        AdminDao adminDao = factory.getAdminDao();
        boolean create = adminDao.create(admin);
        adminDao.close();
        return create;
    }

    public static boolean deleteAdmin(Admin admin) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        AdminDao adminDao = factory.getAdminDao();
        boolean delete = adminDao.delete(admin);
        adminDao.close();
        return delete;
    }

    public static Admin findAdmin(String login, String password) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        AdminDao adminDao = factory.getAdminDao();
        Admin admin = adminDao.findAdmin(login, password);
        adminDao.close();
        return admin;
    }

    public static void updateAdmin(Admin admin) {
        DaoFactory factory = DaoFactory.getInstance(JDBC);
        AdminDao adminDao = factory.getAdminDao();
        adminDao.update(admin);
        adminDao.close();
    }
}
